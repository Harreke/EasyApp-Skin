package com.harreke.easyapp.skin.apcompat.stainer

import android.util.AttributeSet
import android.widget.CheckedTextView
import com.harreke.easyapp.skin.apcompat.helper.SkinCheckedTextViewHelper
import com.harreke.easyapp.skin.core.R

@Suppress("LeakingThis")
open class SkinCheckedTextViewStainer(view: CheckedTextView, attrs: AttributeSet) : SkinTextViewStainer(view, attrs) {
    val checkedTextViewHelper = SkinCheckedTextViewHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        checkedTextViewHelper.update()
    }

    companion object {
        val CheckedTextView.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinCheckedTextViewStainer
    }
}