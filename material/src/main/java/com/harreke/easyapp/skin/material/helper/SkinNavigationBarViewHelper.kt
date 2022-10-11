package com.harreke.easyapp.skin.material.helper

import android.annotation.SuppressLint
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.SupportMenuInflater
import androidx.appcompat.view.menu.MenuBuilder
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarMenuView
import com.google.android.material.navigation.NavigationBarView
import com.harreke.easyapp.skin.core.SkinManager
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.core.menu.SkinMenuBuilder
import com.harreke.easyapp.skin.material.R

@SuppressLint("RestrictedApi")
class SkinNavigationBarViewHelper(private val view: NavigationBarView, attrs: AttributeSet?, private val previewSkinName: String?) {
    private val mContext = view.context
    private val mDummyMenuInflater = SupportMenuInflater(mContext)
    private val mDummyMenu = SkinMenuBuilder()
    private var mMenuId = 0
    private var mBackgroundTintId = 0
    private var mItemBackgroundId = 0
    private var mItemIconTintId = 0
    private var mItemTextColorId = 0

    init {
        val style = mContext.obtainStyledAttributes(attrs, R.styleable.SkinNavigationBarView)
        mMenuId = style.getResourceId(R.styleable.SkinNavigationBarView_menu, 0)
        mBackgroundTintId = style.getResourceId(R.styleable.SkinNavigationBarView_backgroundTint, 0)
        mItemBackgroundId = style.getResourceId(R.styleable.SkinNavigationBarView_itemBackground, 0)
        mItemIconTintId = style.getResourceId(R.styleable.SkinNavigationBarView_itemIconTint, 0)
        mItemTextColorId = style.getResourceId(R.styleable.SkinNavigationBarView_itemTextColor, 0)
        style.recycle()
    }

    fun setMenuResource(menuId: Int) {
        mMenuId = menuId
        updateMenu()
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
        updateMenu()
        updateBackgroundTint()
        updateItemBackground()
        updateItemIconTint()
        updateItemTextColor()
    }
}