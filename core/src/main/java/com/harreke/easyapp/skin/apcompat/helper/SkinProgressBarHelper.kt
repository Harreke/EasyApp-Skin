package com.harreke.easyapp.skin.apcompat.helper

import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.SkinResourcesManager

class SkinProgressBarHelper(private val view: ProgressBar, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mProgressDrawableId = 0
    private var mProgressBackgroundTintId = 0
    private var mProgressTintId = 0
    private var mSecondaryProgressTintId = 0
    private var mIndeterminateTintId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinProgressBar)
        mProgressDrawableId = style.getResourceId(R.styleable.SkinProgressBar_android_progressDrawable, 0)
        mProgressBackgroundTintId = style.getResourceId(R.styleable.SkinProgressBar_android_progressBackgroundTint, 0)
        mProgressTintId = style.getResourceId(R.styleable.SkinProgressBar_android_progressTint, 0)
        mSecondaryProgressTintId = style.getResourceId(R.styleable.SkinProgressBar_android_secondaryProgressTint, 0)
        mIndeterminateTintId = style.getResourceId(R.styleable.SkinProgressBar_android_indeterminateTint, 0)
        style.recycle()
    }

    fun setProgressDrawableResource(@DrawableRes progressDrawableId: Int) {
        mProgressDrawableId = progressDrawableId
        updateProgressDrawable()
    }

    fun setProgressBackgroundTintResource(@ColorRes progressBackgroundTintId: Int) {
        mProgressBackgroundTintId = progressBackgroundTintId
        updateProgressBackgroundTint()
    }

    fun setProgressTintResource(@ColorRes progressTintId: Int) {
        mProgressTintId = progressTintId
        updateProgressTint()
    }

    fun setSecondaryProgressTintResource(@ColorRes secondaryProgressTintId: Int) {
        mSecondaryProgressTintId = secondaryProgressTintId
        updateSecondaryProgressTint()
    }

    fun setIndeterminateTintResource(@ColorRes indeterminateTintId: Int) {
        mIndeterminateTintId = indeterminateTintId
        updateIndeterminateTintId()
    }

    private fun updateProgressDrawable() {
        if (mProgressDrawableId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.progressDrawable = SkinResourcesManager.getSkinDrawable(view.context, mProgressDrawableId, previewSkinName)
    }

    private fun updateProgressBackgroundTint() {
        if (mProgressBackgroundTintId == 0) return
        view.progressBackgroundTintList = SkinResourcesManager.getSkinColorStateList(view.context, mProgressBackgroundTintId, previewSkinName)
    }

    private fun updateProgressTint() {
        if (mProgressTintId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.progressTintList = SkinResourcesManager.getSkinColorStateList(view.context, mProgressTintId, previewSkinName)
    }

    private fun updateSecondaryProgressTint() {
        if (mSecondaryProgressTintId == 0) return
        view.secondaryProgressTintList = SkinResourcesManager.getSkinColorStateList(view.context, mSecondaryProgressTintId, previewSkinName)
    }

    private fun updateIndeterminateTintId() {
        if (mIndeterminateTintId == 0) return
        view.indeterminateTintList = SkinResourcesManager.getSkinColorStateList(view.context, mIndeterminateTintId, previewSkinName)
    }

    fun update() {
        updateProgressDrawable()
        updateProgressBackgroundTint()
        updateProgressTint()
        updateSecondaryProgressTint()
        updateIndeterminateTintId()
    }
}