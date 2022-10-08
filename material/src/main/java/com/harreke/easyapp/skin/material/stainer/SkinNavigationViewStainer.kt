package com.harreke.easyapp.skin.material.stainer

import android.util.AttributeSet
import com.google.android.material.navigation.NavigationView
import com.harreke.easyapp.skin.apcompat.stainer.SkinBackgroundStainer
import com.harreke.easyapp.skin.material.R
import com.harreke.easyapp.skin.material.helper.SkinNavigationViewHelper

@Suppress("LeakingThis")
class SkinNavigationViewStainer(view: NavigationView, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    val navigationViewHelper = SkinNavigationViewHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        navigationViewHelper.update()
    }

    companion object {
        val NavigationView.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinNavigationViewStainer
    }
}