package com.harreke.easyapp.skin.apcompat.stainer

import android.util.AttributeSet
import android.widget.CompoundButton
import com.harreke.easyapp.skin.apcompat.helper.SkinCompoundButtonHelper
import com.harreke.easyapp.skin.core.R

@Suppress("LeakingThis")
open class SkinCompoundButtonStainer(view: CompoundButton, attrs: AttributeSet) : SkinTextViewStainer(view, attrs) {
    val compoundButtonHelper = SkinCompoundButtonHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        compoundButtonHelper.update()
    }

    companion object {
        val CompoundButton.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinCompoundButtonStainer
    }
}