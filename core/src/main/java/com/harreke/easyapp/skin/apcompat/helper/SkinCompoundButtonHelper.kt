package com.harreke.easyapp.skin.apcompat.helper

import android.util.AttributeSet
import android.widget.CompoundButton
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.core.getCompatResourceId

class SkinCompoundButtonHelper(private val view: CompoundButton, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mButtonId = 0
    private var mButtonTintId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinCompoundButton)
        mButtonId = style.getResourceId(R.styleable.SkinCompoundButton_android_button, 0)
        mButtonTintId = style.getCompatResourceId(R.styleable.SkinCompoundButton_android_buttonTint, R.styleable.SkinCompoundButton_buttonTint, 0)
        style.recycle()
    }

    fun setButtonResource(@DrawableRes buttonId: Int) {
        mButtonId = buttonId
        updateButton()
    }

    fun setButtonTintResource(@ColorRes buttonTintId: Int) {
        mButtonTintId = buttonTintId
        updateButtonTint()
    }

    private fun updateButton() {
        if (mButtonId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.buttonDrawable = SkinResourcesManager.getSkinDrawable(view.context, mButtonId, previewSkinName)
    }

    private fun updateButtonTint() {
        if (mButtonTintId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.buttonTintList = SkinResourcesManager.getSkinColorStateList(view.context, mButtonTintId, previewSkinName)
    }

    fun update() {
        updateButton()
        updateButtonTint()
    }
}