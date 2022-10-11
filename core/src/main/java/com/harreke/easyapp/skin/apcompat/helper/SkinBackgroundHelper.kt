package com.harreke.easyapp.skin.apcompat.helper

import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.core.getCompatResourceId

class SkinBackgroundHelper(private val view: View, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mBackgroundId = 0
    private var mBackgroundTintId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinBackground)
        mBackgroundId = style.getCompatResourceId(R.styleable.SkinBackground_android_background, R.styleable.SkinBackground_background, 0)
        mBackgroundTintId = style.getCompatResourceId(R.styleable.SkinBackground_android_backgroundTint, R.styleable.SkinBackground_backgroundTint, 0)
        style.recycle()
    }

    fun setBackgroundResource(@DrawableRes backgroundId: Int) {
        mBackgroundId = backgroundId
        updateBackground()
    }

    fun setBackgroundTintResource(@ColorRes backgroundTintId: Int) {
        mBackgroundTintId = backgroundTintId
        updateBackgroundTint()
    }

    private fun updateBackground() {
        if (mBackgroundId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.background = SkinResourcesManager.getSkinDrawable(view.context, mBackgroundId, previewSkinName)
    }

    private fun updateBackgroundTint() {
        if (mBackgroundTintId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.backgroundTintList = SkinResourcesManager.getSkinColorStateList(view.context, mBackgroundTintId, previewSkinName)
    }

    fun update() {
        updateBackground()
        updateBackgroundTint()
    }
}