package com.harreke.easyapp.skin.material.stainer

import android.util.AttributeSet
import com.google.android.material.navigation.NavigationBarView
import com.harreke.easyapp.skin.apcompat.stainer.SkinBackgroundStainer
import com.harreke.easyapp.skin.material.R
import com.harreke.easyapp.skin.material.helper.SkinNavigationBarViewHelper

@Suppress("LeakingThis")
class SkinNavigationBarViewStainer(view: NavigationBarView, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    val navigationBarViewHelper = SkinNavigationBarViewHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        navigationBarViewHelper.update()
    }

    companion object {
        val NavigationBarView.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinNavigationBarViewStainer
    }
}