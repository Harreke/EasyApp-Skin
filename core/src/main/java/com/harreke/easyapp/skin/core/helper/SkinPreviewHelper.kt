package com.harreke.easyapp.skin.core.helper

import android.util.AttributeSet
import android.view.View
import com.harreke.easyapp.skin.core.R

object SkinPreviewHelper {
    fun parseAttrs(view: View, attrs: AttributeSet?): String? {
        //        if (!view.isInEditMode) return
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinPreview)
        val previewSkinName = style.getString(R.styleable.SkinPreview_previewSkinName)
        style.recycle()
        return previewSkinName
    }
}