package com.harreke.easyapp.skin.apcompat.stainer

import android.util.AttributeSet
import android.widget.ImageView
import com.harreke.easyapp.skin.apcompat.helper.SkinImageViewHelper
import com.harreke.easyapp.skin.core.R

@Suppress("LeakingThis")
open class SkinImageViewStainer(view: ImageView, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    val imageViewHelper = SkinImageViewHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        imageViewHelper.update()
    }

    companion object {
        val ImageView.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinImageViewStainer
    }
}