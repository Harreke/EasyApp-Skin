package com.harreke.easyapp.skin.material.stainer

import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.harreke.easyapp.skin.apcompat.stainer.SkinBackgroundStainer
import com.harreke.easyapp.skin.material.R
import com.harreke.easyapp.skin.material.helper.SkinTextInputLayoutHelper

@Suppress("LeakingThis")
class SkinTextInputLayoutStainer(view: TextInputLayout, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    val textInputLayoutHelper = SkinTextInputLayoutHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        textInputLayoutHelper.update()
    }

    companion object {
        val TextInputLayout.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinTextInputLayoutStainer
    }
}