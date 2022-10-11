package com.harreke.easyapp.skin.apcompat.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.Menu
import androidx.annotation.StyleRes
import androidx.appcompat.view.SupportMenuInflater
import androidx.appcompat.widget.Toolbar
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.core.menu.SkinMenuBuilder

@SuppressLint("RestrictedApi")
class SkinToolbarHelper(private val view: Toolbar, attrs: AttributeSet?, private val previewSkinName: String?) {
    private val mContext = view.context
    private val mDummyMenuInflater = SupportMenuInflater(mContext)
    private val mDummyMenu = SkinMenuBuilder()
    private var mMenuId = 0
    private var mPopupTHemeId = 0
    private var mTitleTextColorId = 0
    private var mTitleTextColorStateList: ColorStateList? = null
    private var mSubtitleTextColorId = 0
    private var mTitleTextAppearanceId = 0
    private var mSubtitleTextAppearanceId = 0
    private var mNavigationIconId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinToolbar)
        mMenuId = style.getResourceId(R.styleable.SkinToolbar_menu, 0)
        mPopupTHemeId = style.getResourceId(R.styleable.SkinToolbar_popupTheme, 0)
        mTitleTextColorId = style.getResourceId(R.styleable.SkinToolbar_titleTextColor, 0)
        mSubtitleTextColorId = style.getResourceId(R.styleable.SkinToolbar_subtitleTextColor, 0)
        mTitleTextAppearanceId = style.getResourceId(R.styleable.SkinToolbar_titleTextAppearance, 0)
        mSubtitleTextAppearanceId = style.getResourceId(R.styleable.SkinToolbar_subtitleTextAppearance, 0)
        mNavigationIconId = style.getResourceId(R.styleable.SkinToolbar_navigationIcon, 0)
        style.recycle()
    }

    fun setMenuResource(menuId: Int) {
        mMenuId = menuId
        updateMenu()
    }

    fun setPopupTheme(@StyleRes popupThemeId: Int) {
        mPopupTHemeId = popupThemeId
        updatePopupTheme()
    }

    fun setTitleTextColorResource(titleTextColorId: Int) {
        mTitleTextColorId = titleTextColorId
        updateTitleTextColor()
    }

    fun setSubtitleTextColorResource(subtitleTextColorId: Int) {
        mSubtitleTextColorId = subtitleTextColorId
        updateSubtitleTextColor()
    }

    fun setTitleTextAppearanceResource(@StyleRes titleTextAppearanceId: Int) {
        mTitleTextAppearanceId = titleTextAppearanceId
        updateTitleTextAppearanceColor()
    }

    fun setSubtitleTextAppearanceResource(@StyleRes subtitleTextAppearanceId: Int) {
        mSubtitleTextAppearanceId = subtitleTextAppearanceId
        updateSubtitleTextAppearanceColor()
    }

    fun setNavigationIconResource(navigationIconId: Int) {
        mNavigationIconId = navigationIconId
        updateNavigationIcon()
    }

    private fun updateMenu() {
        if (mMenuId == 0) return
        val dummyMenu = mDummyMenu
        dummyMenu.clear()
        mDummyMenuInflater.inflate(mMenuId, dummyMenu)
        updateMenu(mContext, view.menu, dummyMenu, previewSkinName)
    }

    private fun updateMenu(context: Context, menu: Menu, dummyMenu: SkinMenuBuilder, previewSkinName: String?) {
        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            val menuItemId = menuItem.itemId
            val dummyMenuItem = dummyMenu.findItem(menuItemId) ?: continue
            val dummyMenuIconId = dummyMenuItem.itemIconId
            menuItem.icon = SkinResourcesManager.getSkinDrawable(context, dummyMenuIconId, previewSkinName)
            val subMenu = menuItem.subMenu ?: continue
            val dummySubMenu = dummyMenuItem.subMenu ?: continue
            updateMenu(context, subMenu, dummySubMenu, previewSkinName)
        }
    }

    private fun updatePopupTheme() {
        if (mPopupTHemeId == 0) return
        val skinPopupThemId = SkinResourcesManager.getSkinResourceId(mContext, mPopupTHemeId, "style", previewSkinName)
        view.popupTheme = skinPopupThemId
        view.menu.clear()
        try {
            val menuViewField = view::class.java.getDeclaredField("mMenuView")
            menuViewField.isAccessible = true
            menuViewField.set(view, null)
        } catch (_: Exception) {
        }
        view.inflateMenu(mMenuId)
        updateMenu()
    }

    private fun updateTitleTextColor() {
        if (mTitleTextColorId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        val titleTextColorStateList = SkinResourcesManager.getSkinColorStateList(view.context, mTitleTextColorId, previewSkinName) ?: return
        view.setTitleTextColor(titleTextColorStateList)
        mTitleTextColorStateList = titleTextColorStateList
    }

    private fun updateSubtitleTextColor() {
        if (mSubtitleTextColorId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        val subtitleTextColorStateList = SkinResourcesManager.getSkinColorStateList(view.context, mSubtitleTextColorId, previewSkinName) ?: return
        view.setSubtitleTextColor(subtitleTextColorStateList)
    }

    private fun updateTitleTextAppearanceColor() {
        if (mTitleTextAppearanceId == 0) return
        val titleTextColorStateList = SkinResourcesManager.getSkinTextAppearanceColorStateList(view.context, mTitleTextAppearanceId, previewSkinName) ?: return
        view.setTitleTextColor(titleTextColorStateList)
        mTitleTextColorStateList = titleTextColorStateList
    }

    private fun updateSubtitleTextAppearanceColor() {
        if (mSubtitleTextAppearanceId == 0) return
        val subtitleTextColorStateList = SkinResourcesManager.getSkinTextAppearanceColorStateList(view.context, mSubtitleTextAppearanceId, previewSkinName) ?: return
        view.setSubtitleTextColor(subtitleTextColorStateList)
    }

    private fun updateNavigationIcon() {
        if (mNavigationIconId == 0) return
        view.navigationIcon = SkinResourcesManager.getSkinDrawable(view.context, mNavigationIconId, previewSkinName)
    }

    fun update() {
        updateMenu()
        updatePopupTheme()
        updateTitleTextColor()
        updateSubtitleTextColor()
        updateTitleTextAppearanceColor()
        updateSubtitleTextAppearanceColor()
        updateNavigationIcon()
    }
}