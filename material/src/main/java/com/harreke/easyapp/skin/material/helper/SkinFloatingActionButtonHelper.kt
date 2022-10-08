package com.harreke.easyapp.skin.material.helper

import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.material.R

class SkinFloatingActionButtonHelper(private val view: FloatingActionButton, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mBackgroundTintId = 0
    private var mRippleColorId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinFloatingActionButton)
        mBackgroundTintId = style.getResourceId(R.styleable.SkinFloatingActionButton_backgroundTint, 0)
        mRippleColorId = style.getResourceId(R.styleable.SkinFloatingActionButton_rippleColor, 0)
        style.recycle()
    }

    fun setBackgroundTintResource(backgroundTintId: Int) {
        mBackgroundTintId = backgroundTintId
        updateBackgroundTint()
    }

    fun setRippleColorResource(rippleColorId: Int) {
        mRippleColorId = rippleColorId
        updateRippleColor()
    }

    private fun updateBackgroundTint() {
        if (mBackgroundTintId == 0) return
        view.backgroundTintList = SkinResourcesManager.getSkinColorStateList(view.context, mBackgroundTintId, previewSkinName)
    }

    private fun updateRippleColor() {
        if (mRippleColorId == 0) return
        view.setRippleColor(SkinResourcesManager.getSkinColorStateList(view.context, mRippleColorId, previewSkinName))
    }

    fun update() {
        updateBackgroundTint()
        updateRippleColor()
    }
}