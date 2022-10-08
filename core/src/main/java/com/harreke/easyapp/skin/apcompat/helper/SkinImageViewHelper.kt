package com.harreke.easyapp.skin.apcompat.helper

import android.util.AttributeSet
import android.widget.ImageView
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.SkinResourcesManager

class SkinImageViewHelper(private val view: ImageView, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mImageId = 0
    private var mImageTintId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinImageView)
        mImageId = style.getResourceId(R.styleable.SkinImageView_android_src, 0)
        mImageTintId = style.getResourceId(R.styleable.SkinImageView_android_tint, 0)
        style.recycle()
    }

    fun setImageResource(imageId: Int) {
        mImageId = imageId
        updateImage()
    }

    fun setImageTintResource(imageTintId: Int) {
        mImageTintId = imageTintId
        updateImageTint()
    }

    private fun updateImage() {
        if (mImageId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.setImageDrawable(SkinResourcesManager.getSkinDrawable(view.context, mImageId, previewSkinName))
    }

    private fun updateImageTint() {
        if (mImageTintId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.imageTintList = SkinResourcesManager.getSkinColorStateList(view.context, mImageTintId, previewSkinName)
    }

    fun update() {
        updateImage()
        updateImageTint()
    }
}