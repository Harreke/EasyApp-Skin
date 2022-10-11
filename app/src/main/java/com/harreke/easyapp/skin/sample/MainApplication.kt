package com.harreke.easyapp.skin.sample

import android.app.Application
import com.harreke.easyapp.skin.cardview.SkinCardViewStainerWrapper
import com.harreke.easyapp.skin.core.SkinManager
import com.harreke.easyapp.skin.material.SkinMaterialStainerWrapper

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        SkinManager
            .addStainerWrapper(SkinMaterialStainerWrapper())
            .addStainerWrapper(SkinCardViewStainerWrapper())
            .loadSkin(this)
    }
}