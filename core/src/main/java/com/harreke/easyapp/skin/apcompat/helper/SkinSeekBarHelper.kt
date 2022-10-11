package com.harreke.easyapp.skin.apcompat.helper

import android.util.AttributeSet
import android.widget.SeekBar
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.core.getCompatResourceId

class SkinSeekBarHelper(private val view: SeekBar, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mThumbId = 0
    private var mThumbTintId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinSeekBar)
        mThumbId = style.getResourceId(R.styleable.SkinSeekBar_android_thumb, 0)
        mThumbTintId = style.getCompatResourceId(R.styleable.SkinSeekBar_android_thumbTint, R.styleable.SkinSeekBar_thumbTint, 0)
        style.recycle()
    }

    fun setThumbResource(@DrawableRes thumbId: Int) {
        mThumbId = thumbId
        updateThumb()
    }

    fun setThumbTintResource(@ColorRes thumbTintId: Int) {
        mThumbTintId = thumbTintId
        updateThumbTint()
    }

    private fun updateThumb() {
        if (mThumbId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.thumb = SkinResourcesManager.getSkinDrawable(view.context, mThumbId, previewSkinName)
    }

    private fun updateThumbTint() {
        if (mThumbTintId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.thumbTintList = SkinResourcesManager.getSkinColorStateList(view.context, mThumbTintId, previewSkinName)
    }

    fun update() {
        updateThumb()
        updateThumbTint()
    }
}