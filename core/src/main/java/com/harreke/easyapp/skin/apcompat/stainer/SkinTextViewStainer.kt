package com.harreke.easyapp.skin.apcompat.stainer

import android.util.AttributeSet
import android.widget.TextView
import com.harreke.easyapp.skin.apcompat.helper.SkinTextViewHelper
import com.harreke.easyapp.skin.core.R

@Suppress("LeakingThis")
open class SkinTextViewStainer(view: TextView, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    val textViewHelper = SkinTextViewHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        textViewHelper.update()
    }

    companion object {
        val TextView.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinTextViewStainer
    }
}