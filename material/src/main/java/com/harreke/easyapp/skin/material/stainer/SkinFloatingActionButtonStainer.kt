package com.harreke.easyapp.skin.material.stainer

import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.harreke.easyapp.skin.apcompat.stainer.SkinBackgroundStainer
import com.harreke.easyapp.skin.material.R
import com.harreke.easyapp.skin.material.helper.SkinFloatingActionButtonHelper

@Suppress("LeakingThis")
class SkinFloatingActionButtonStainer(view: FloatingActionButton, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    val navigationViewHelper = SkinFloatingActionButtonHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        navigationViewHelper.update()
    }

    companion object {
        val FloatingActionButton.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinFloatingActionButtonStainer
    }
}