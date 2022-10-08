package com.harreke.easyapp.skin.material.helper

import android.util.AttributeSet
import com.google.android.material.navigation.NavigationView
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.material.R

class SkinNavigationViewHelper(private val view: NavigationView, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mItemIconTintId = 0
    private var mItemTextColorId = 0
    private var mItemBackgroundId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinNavigationView)
        mItemIconTintId = style.getResourceId(R.styleable.SkinNavigationView_itemIconTint, 0)
        mItemTextColorId = style.getResourceId(R.styleable.SkinNavigationView_itemTextColor, 0)
        mItemBackgroundId = style.getResourceId(R.styleable.SkinNavigationView_itemBackground, 0)
        style.recycle()
    }

    fun setItemIconTintResource(itemIconTintId: Int) {
        mItemIconTintId = itemIconTintId
        updateItemIconTint()
    }

    fun setItemTextColorResource(itemTextColorId: Int) {
        mItemTextColorId = itemTextColorId
        updateItemTextColor()
    }

    fun setItemBackgroundResource(itemBackgroundId: Int) {
        mItemBackgroundId = itemBackgroundId
        updateItemBackground()
    }

    private fun updateItemIconTint() {
        if (mItemIconTintId == 0) return
        view.itemIconTintList = SkinResourcesManager.getSkinColorStateList(view.context, mItemIconTintId, previewSkinName)
    }

    private fun updateItemTextColor() {
        if (mItemTextColorId == 0) return
        view.itemTextColor = SkinResourcesManager.getSkinColorStateList(view.context, mItemTextColorId, previewSkinName)
    }

    private fun updateItemBackground() {
        if (mItemBackgroundId == 0) return
        view.itemBackground = SkinResourcesManager.getSkinDrawable(view.context, mItemBackgroundId, previewSkinName)
    }

    fun update() {
        updateItemIconTint()
        updateItemTextColor()
        updateItemBackground()
    }
}