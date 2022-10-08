package com.harreke.easyapp.skin.material.helper

import android.util.AttributeSet
import com.google.android.material.navigation.NavigationBarView
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.material.R

class SkinNavigationBarViewHelper(private val view: NavigationBarView, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mBackgroundTintId = 0
    private var mItemBackgroundId = 0
    private var mItemIconTintId = 0
    private var mItemTextColorId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinNavigationBarView)
        mBackgroundTintId = style.getResourceId(R.styleable.SkinNavigationBarView_backgroundTint, 0)
        mItemBackgroundId = style.getResourceId(R.styleable.SkinNavigationBarView_itemBackground, 0)
        mItemIconTintId = style.getResourceId(R.styleable.SkinNavigationBarView_itemIconTint, 0)
        mItemTextColorId = style.getResourceId(R.styleable.SkinNavigationBarView_itemTextColor, 0)
        style.recycle()
    }

    fun setBackgroundTintResource(backgroundTintId: Int) {
        mBackgroundTintId = backgroundTintId
        updateBackgroundTint()
    }

    fun setItemBackgroundResource(itemBackgroundId: Int) {
        mItemBackgroundId = itemBackgroundId
        updateItemBackground()
    }

    fun setItemIconTintResource(itemIconTintId: Int) {
        mItemIconTintId = itemIconTintId
        updateItemIconTint()
    }

    fun setItemTextColorResource(itemTextColorId: Int) {
        mItemTextColorId = itemTextColorId
        updateItemTextColor()
    }

    private fun updateBackgroundTint() {
        if (mBackgroundTintId == 0) return
        view.backgroundTintList = SkinResourcesManager.getSkinColorStateList(view.context, mBackgroundTintId, previewSkinName)
    }

    private fun updateItemBackground() {
        if (mItemBackgroundId == 0) return
        view.itemBackground = SkinResourcesManager.getSkinDrawable(view.context, mItemBackgroundId, previewSkinName)
    }

    private fun updateItemIconTint() {
        if (mItemIconTintId == 0) return
        view.itemIconTintList = SkinResourcesManager.getSkinColorStateList(view.context, mItemIconTintId, previewSkinName)
    }

    private fun updateItemTextColor() {
        if (mItemTextColorId == 0) return
        view.itemTextColor = SkinResourcesManager.getSkinColorStateList(view.context, mItemTextColorId, previewSkinName)
    }

    fun update() {
        updateBackgroundTint()
        updateItemBackground()
        updateItemIconTint()
        updateItemTextColor()
    }
}