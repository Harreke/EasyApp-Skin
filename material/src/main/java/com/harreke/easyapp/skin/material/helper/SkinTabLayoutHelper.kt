package com.harreke.easyapp.skin.material.helper

import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.material.R

class SkinTabLayoutHelper(private val view: TabLayout, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mTabIndicatorColorId = 0
    private var mTabBackgroundId = 0
    private var mTabIndicatorId = 0
    private var mTabTextColorId = 0
    private var mTabSelectedTextColorId = 0
    private var mTabIconTintId = 0
    private var mTabRippleColorId = 0
    private val mBaseBackgroundDrawableField = view::class.java.getDeclaredField("baseBackgroundDrawable").apply {
        isAccessible = true
    }

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinTabLayout)
        mTabIndicatorColorId = style.getResourceId(R.styleable.SkinTabLayout_tabIndicatorColor, 0)
        mTabBackgroundId = style.getResourceId(R.styleable.SkinTabLayout_tabBackground, 0)
        mTabIndicatorId = style.getResourceId(R.styleable.SkinTabLayout_tabIndicator, 0)
        mTabTextColorId = style.getResourceId(R.styleable.SkinTabLayout_tabTextColor, 0)
        mTabSelectedTextColorId = style.getResourceId(R.styleable.SkinTabLayout_tabSelectedTextColor, 0)
        mTabIconTintId = style.getResourceId(R.styleable.SkinTabLayout_tabIconTint, 0)
        mTabRippleColorId = style.getResourceId(R.styleable.SkinTabLayout_tabRippleColor, 0)
        style.recycle()
    }

    fun setTabIndicatorColorResource(tabIndicatorColorId: Int) {
        mTabIndicatorColorId = tabIndicatorColorId
        updateTabIndicatorColor()
    }

    fun setTabBackgroundResource(tabBackgroundId: Int) {
        mTabBackgroundId = tabBackgroundId
        updateTabBackground()
    }

    fun setTabIndicatorResource(tabIndicatorId: Int) {
        mTabIndicatorId = tabIndicatorId
        updateTabIndicator()
    }

    fun setTabTextColorResource(tabTextColorId: Int) {
        mTabTextColorId = tabTextColorId
        updateTabTextColor()
    }

    fun setTabSelectedTextColorResource(tabSelectedTextColorId: Int) {
        mTabSelectedTextColorId = tabSelectedTextColorId
        updateTabSelectedTextColor()
    }

    fun seTabIconTintResource(tabIconTintId: Int) {
        mTabIconTintId = tabIconTintId
        updateTabIconTint()
    }

    fun setTabRippleColorResource(tabRippleColorId: Int) {
        mTabRippleColorId = tabRippleColorId
        updateTabRippleColor()
    }

    private fun updateTabIndicatorColor() {
        if (mTabIndicatorColorId == 0) return
        view.setSelectedTabIndicatorColor(SkinResourcesManager.getSkinColor(view.context, mTabIndicatorColorId, previewSkinName))
    }

    private fun updateTabBackground() {
        if (mTabBackgroundId == 0) return
        val tabBackground = SkinResourcesManager.getSkinDrawable(view.context, mTabBackgroundId, previewSkinName)
        mBaseBackgroundDrawableField.set(view, tabBackground)
        view.invalidate()
    }

    private fun updateTabIndicator() {
        if (mTabIndicatorId == 0) return
        view.setSelectedTabIndicator(SkinResourcesManager.getSkinDrawable(view.context, mTabIndicatorId, previewSkinName))
    }

    private fun updateTabTextColor() {
        if (mTabTextColorId == 0) return
        view.tabTextColors = SkinResourcesManager.getSkinColorStateList(view.context, mTabTextColorId, previewSkinName)
    }

    private fun updateTabSelectedTextColor() {
        if (mTabSelectedTextColorId == 0) return
        val tabTextColor = view.tabTextColors?.defaultColor ?: 0
        val tabSelectedTextColor = SkinResourcesManager.getSkinColor(view.context, mTabSelectedTextColorId, previewSkinName)
        view.setTabTextColors(tabTextColor, tabSelectedTextColor)
    }

    private fun updateTabTextColors() {
        if (mTabTextColorId == 0 || mTabSelectedTextColorId == 0) return
        val view = view
        val context = view.context
        val previewSkinName = previewSkinName
        val tabTextColor = SkinResourcesManager.getSkinColor(context, mTabTextColorId, previewSkinName)
        val tabSelectedTextColor = SkinResourcesManager.getSkinColor(context, mTabSelectedTextColorId, previewSkinName)
        view.setTabTextColors(tabTextColor, tabSelectedTextColor)
    }

    private fun updateTabIconTint() {
        if (mTabIconTintId == 0) return
        view.tabIconTint = SkinResourcesManager.getSkinColorStateList(view.context, mTabIconTintId, previewSkinName)
    }

    private fun updateTabRippleColor() {
        if (mTabRippleColorId == 0) return
        view.tabRippleColor = SkinResourcesManager.getSkinColorStateList(view.context, mTabRippleColorId, previewSkinName)
    }

    fun update() {
        updateTabIndicatorColor()
        updateTabBackground()
        updateTabIndicator()
        updateTabTextColors()
        updateTabIconTint()
        updateTabRippleColor()
    }
}