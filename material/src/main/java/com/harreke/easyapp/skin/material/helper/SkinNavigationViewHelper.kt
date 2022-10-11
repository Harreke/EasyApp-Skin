package com.harreke.easyapp.skin.material.helper

import android.annotation.SuppressLint
import android.util.AttributeSet
import androidx.appcompat.view.SupportMenuInflater
import com.google.android.material.navigation.NavigationView
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.core.menu.SkinMenuBuilder
import com.harreke.easyapp.skin.material.R

@SuppressLint("RestrictedApi")
class SkinNavigationViewHelper(private val view: NavigationView, attrs: AttributeSet?, private val previewSkinName: String?) {
    private val mContext = view.context
    private val mDummyMenuInflater = SupportMenuInflater(mContext)
    private val mDummyMenu = SkinMenuBuilder()
    private var mMenuId = 0
    private var mItemIconTintId = 0
    private var mItemTextColorId = 0
    private var mItemTextAppearanceId = 0
    private var mItemBackgroundId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinNavigationView)
        mMenuId = style.getResourceId(R.styleable.SkinNavigationView_menu, 0)
        mItemIconTintId = style.getResourceId(R.styleable.SkinNavigationView_itemIconTint, 0)
        mItemTextColorId = style.getResourceId(R.styleable.SkinNavigationView_itemTextColor, 0)
        mItemTextAppearanceId = style.getResourceId(R.styleable.SkinNavigationView_itemTextAppearance, 0)
        mItemBackgroundId = style.getResourceId(R.styleable.SkinNavigationView_itemBackground, 0)
        style.recycle()
    }

    fun setMenuResource(menuId: Int) {
        mMenuId = menuId
        updateMenu()
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

    private fun updateMenu() {
        if (mMenuId == 0) return
        val context = mContext
        val previewSkinName = previewSkinName
        val dummyMenu = mDummyMenu
        dummyMenu.clear()
        mDummyMenuInflater.inflate(mMenuId, dummyMenu)
        val menu = view.menu
        if (mItemIconTintId == 0) {
            view.itemIconTintList = null
        }
        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            val menuItemId = menuItem.itemId
            val dummyMenuItem = dummyMenu.findItem(menuItemId) ?: continue
            val dummyMenuIconId = dummyMenuItem.itemIconId
            menuItem.icon = SkinResourcesManager.getSkinDrawable(context, dummyMenuIconId, previewSkinName)
        }
    }

    private fun updateItemIconTint() {
        if (mItemIconTintId == 0) return
        view.itemIconTintList = SkinResourcesManager.getSkinColorStateList(mContext, mItemIconTintId, previewSkinName)
    }

    private fun updateItemTextColor() {
        if (mItemTextColorId == 0) return
        view.itemTextColor = SkinResourcesManager.getSkinColorStateList(mContext, mItemTextColorId, previewSkinName)
    }

    private fun updateItemTextAppearanceColor() {
        if (mItemTextAppearanceId == 0) return
        view.itemTextColor = SkinResourcesManager.getSkinTextAppearanceColorStateList(mContext, mItemTextAppearanceId, previewSkinName)
    }

    private fun updateItemBackground() {
        if (mItemBackgroundId == 0) return
        view.setItemBackgroundResource(SkinResourcesManager.getSkinResourceId(mContext, mItemBackgroundId, "color", previewSkinName))
    }

    fun update() {
        updateMenu()
        updateItemIconTint()
        updateItemTextColor()
        updateItemTextAppearanceColor()
        updateItemBackground()
    }
}