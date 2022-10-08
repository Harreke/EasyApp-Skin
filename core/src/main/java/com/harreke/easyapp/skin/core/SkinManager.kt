package com.harreke.easyapp.skin.core

import android.content.Context
import androidx.appcompat.app.SkinAppCompatDelegate
import androidx.core.content.edit
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

object SkinManager {
    private const val PREFERENCE_NAME = "skin_core"
    private const val PREFERENCE_SKIN_NAME = "skin_name"
    val skinName
        get() = SkinResourcesManager.skinName
    private val mSkinObserver = HashSet<ISkinObserver>()
    private val mSkinLocker = ReentrantReadWriteLock()

    private fun Context?.sharedPreference(name: String) = this?.getSharedPreferences(name, Context.MODE_PRIVATE)

    fun loadSkin(context: Context?): SkinManager {
        if (context == null) return this
        SkinResourcesManager.skinName = context.sharedPreference(PREFERENCE_NAME)?.getString(PREFERENCE_SKIN_NAME, null).orEmpty()
        return this
    }

    fun loadSkin(context: Context?, skinName: String?): SkinManager {
        if (context == null || skinName.isNullOrEmpty()) return this
        if (this.skinName == skinName) return this
        context.sharedPreference(PREFERENCE_NAME)?.edit {
            putString(PREFERENCE_SKIN_NAME, skinName)
        }
        SkinResourcesManager.skinName = skinName
        mSkinLocker.read {
            val local = HashSet(mSkinObserver)
            for (observer in local) {
                observer.updateSkin()
            }
        }
        return this
    }

    fun resetSkin(context: Context?): SkinManager {
        if (context == null) return this
        if (SkinResourcesManager.skinName.isEmpty()) return this
        context.sharedPreference(PREFERENCE_NAME)?.edit {
            remove(PREFERENCE_SKIN_NAME)
        }
        SkinResourcesManager.skinName = ""
        mSkinLocker.read {
            val local = HashSet(mSkinObserver)
            for (observer in local) {
                observer.updateSkin()
            }
        }
        return this
    }

    fun addObserver(observer: ISkinObserver): SkinManager {
        mSkinLocker.write {
            mSkinObserver += observer
        }
        return this
    }

    fun removeObserver(observer: ISkinObserver): SkinManager {
        mSkinLocker.write {
            mSkinObserver -= observer
        }
        return this
    }

    fun addStainerWrapper(wrapper: ISkinStainerWrapper): SkinManager {
        SkinAppCompatDelegate.skinViewStainerWrappers.add(wrapper)
        return this
    }
}