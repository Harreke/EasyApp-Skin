package com.harreke.easyapp.skin.apcompat.helper

import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.SkinResourcesManager

class SkinToolbarHelper(private val view: Toolbar, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mTitleTextColorId = 0
    private var mSubtitleTextColorId = 0
    private var mNavigationIconId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinToolbar)
        mTitleTextColorId = style.getResourceId(R.styleable.SkinToolbar_titleTextColor, 0)
        mSubtitleTextColorId = style.getResourceId(R.styleable.SkinToolbar_subtitleTextColor, 0)
        mNavigationIconId = style.getResourceId(R.styleable.SkinToolbar_navigationIcon, 0)
        style.recycle()
    }

    fun setTitleTextColorResource(titleTextColorId: Int) {
        mTitleTextColorId = titleTextColorId
        updateTitleTextColor()
    }

    fun setSubtitleTextColorResource(subtitleTextColorId: Int) {
        mSubtitleTextColorId = subtitleTextColorId
        updateSubtitleTextColor()
    }

    fun setNavigationIconResource(navigationIconId: Int) {
        mNavigationIconId = navigationIconId
        updateNavigationIcon()
    }

    private fun updateTitleTextColor() {
        if (mTitleTextColorId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.setTitleTextColor(SkinResourcesManager.getSkinColor(view.context, mTitleTextColorId, previewSkinName))
    }

    private fun updateSubtitleTextColor() {
        if (mSubtitleTextColorId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.setSubtitleTextColor(SkinResourcesManager.getSkinColor(view.context, mSubtitleTextColorId, previewSkinName))
    }

    private fun updateNavigationIcon() {
        if (mNavigationIconId == 0) return
        view.navigationIcon = SkinResourcesManager.getSkinDrawable(view.context, mNavigationIconId, previewSkinName)
    }

    fun update() {
        updateTitleTextColor()
        updateSubtitleTextColor()
        updateNavigationIcon()
    }
}