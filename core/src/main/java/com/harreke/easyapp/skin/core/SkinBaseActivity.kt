package com.harreke.easyapp.skin.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class SkinBaseActivity : AppCompatActivity(), ISkinObserver {
    override fun getDelegate() = SkinManager.createDelegate(this) ?: super.getDelegate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SkinManager.addObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        SkinManager.destroyDelegate(this)
        SkinManager.removeObserver(this)
    }

    final override fun updateSkin() {
        SkinManager.updateDelegate(this)
        applySkin()
    }

    protected open fun applySkin() {
        //TODO custom skin apply action
    }
}