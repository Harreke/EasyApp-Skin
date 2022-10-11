package com.harreke.easyapp.skin.core

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
    private val mDelegateMap = HashMap<Activity, SkinAppCompatDelegate>()

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

    fun createDelegate(activity: AppCompatActivity): AppCompatDelegate? {
        if (activity::class.java.getAnnotation(NoSkin::class.java) != null) return null
        var delegate = mDelegateMap[activity]
        if (delegate == null) {
            delegate = SkinAppCompatDelegate(activity)
            mDelegateMap[activity] = delegate
        }
        return delegate
    }

    fun destroyDelegate(activity: AppCompatActivity) {
        mDelegateMap.remove(activity)?.destroy()
    }

    fun updateDelegate(activity: AppCompatActivity) {
        mDelegateMap[activity]?.updateSkin()
    }
}