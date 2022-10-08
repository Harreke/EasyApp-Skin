package com.harreke.easyapp.skin.material.helper

import android.util.AttributeSet
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.material.R

class SkinCollapsingToolbarLayoutHelper(private val view: CollapsingToolbarLayout, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mExpandedTitleTextAppearanceId = 0
    private var mCollapsedTitleTextAppearanceId = 0
    private var mContentScrimId = 0
    private var mStatusBarScrimId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinCollapsingToolbarLayout)
        mExpandedTitleTextAppearanceId = style.getResourceId(R.styleable.SkinCollapsingToolbarLayout_expandedTitleTextAppearance, 0)
        mCollapsedTitleTextAppearanceId = style.getResourceId(R.styleable.SkinCollapsingToolbarLayout_collapsedTitleTextAppearance, 0)
        mContentScrimId = style.getResourceId(R.styleable.SkinCollapsingToolbarLayout_contentScrim, 0)
        mStatusBarScrimId = style.getResourceId(R.styleable.SkinCollapsingToolbarLayout_statusBarScrim, 0)
        style.recycle()
    }

    fun setExpandedTitleTextColorResource(expandedTitleTextColorId: Int) {
        mExpandedTitleTextAppearanceId = expandedTitleTextColorId
        updateExpandedTitleTextAppearance()
    }

    fun setCollapsedTitleTextColorResource(collapsedTitleTextColorId: Int) {
        mCollapsedTitleTextAppearanceId = collapsedTitleTextColorId
        updateCollapsedTitleTextColor()
    }

    fun setContentScrimResource(contentScrimId: Int) {
        mContentScrimId = contentScrimId
        updateContentScrim()
    }

    fun setStatusBarScrimResource(statusBarScrimId: Int) {
        mStatusBarScrimId = statusBarScrimId
        updateStatusBarScrim()
    }

    private fun updateExpandedTitleTextAppearance() {
        if (mExpandedTitleTextAppearanceId == 0) return
        view.setExpandedTitleColor(SkinResourcesManager.getSkinTextAppearanceColor(view.context, mExpandedTitleTextAppearanceId, previewSkinName))
    }

    private fun updateCollapsedTitleTextColor() {
        if (mCollapsedTitleTextAppearanceId == 0) return
        view.setCollapsedTitleTextColor(SkinResourcesManager.getSkinTextAppearanceColor(view.context, mCollapsedTitleTextAppearanceId, previewSkinName))
    }

    private fun updateContentScrim() {
        if (mContentScrimId == 0) return
        view.contentScrim = SkinResourcesManager.getSkinDrawable(view.context, mContentScrimId, previewSkinName)
    }

    private fun updateStatusBarScrim() {
        if (mStatusBarScrimId == 0) return
        view.statusBarScrim = SkinResourcesManager.getSkinDrawable(view.context, mStatusBarScrimId, previewSkinName)
    }

    fun update() {
        updateExpandedTitleTextAppearance()
        updateCollapsedTitleTextColor()
        updateContentScrim()
    }
}