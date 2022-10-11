package com.harreke.easyapp.skin.core.menu

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.MenuItem
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.internal.view.SupportMenuItem
import androidx.core.view.ActionProvider

@SuppressLint("RestrictedApi")
class SkinMenuItem(private val mGroupId: Int, private val mItemId: Int) : SupportMenuItem {
    @DrawableRes
    var itemIconId = 0
    var subMenu: SkinSubMenuBuilder? = null

    override fun getGroupId() = mGroupId

    override fun getItemId() = mItemId

    override fun getOrder() = 0

    override fun setTitle(title: CharSequence?) = this

    override fun setTitle(title: Int) = this

    override fun getTitle() = null

    override fun setTitleCondensed(title: CharSequence?) = this

    override fun getTitleCondensed() = null

    override fun setIcon(icon: Drawable?): SkinMenuItem {
        itemIconId = 0
        return this
    }

    override fun setIcon(iconRes: Int): MenuItem {
        itemIconId = iconRes
        return this
    }

    override fun getIcon() = null

    override fun setIconTintList(tint: ColorStateList?) = this

    override fun getIconTintList(): ColorStateList? = null

    override fun setIconTintMode(tintMode: PorterDuff.Mode?) = this

    override fun getIconTintMode(): PorterDuff.Mode? = null

    override fun setIntent(intent: Intent?) = this

    override fun getIntent() = null

    override fun setShortcut(numericChar: Char, alphaChar: Char, numericModifiers: Int, alphaModifiers: Int) = this

    override fun setShortcut(numericChar: Char, alphaChar: Char) = this

    override fun setNumericShortcut(numericChar: Char, numericModifiers: Int) = this

    override fun setNumericShortcut(numericChar: Char) = this

    override fun getNumericShortcut() = ' '

    override fun getNumericModifiers() = 0

    override fun setAlphabeticShortcut(alphaChar: Char, alphaModifiers: Int) = this

    override fun setAlphabeticShortcut(alphaChar: Char) = this

    override fun getAlphabeticShortcut() = ' '

    override fun getAlphabeticModifiers() = 0

    override fun setCheckable(checkable: Boolean) = this

    override fun isCheckable() = false

    override fun setChecked(checked: Boolean) = this

    override fun isChecked() = false

    override fun setVisible(visible: Boolean) = this

    override fun isVisible() = false

    override fun setEnabled(enabled: Boolean) = this

    override fun isEnabled() = false

    override fun hasSubMenu() = false

    override fun getSubMenu() = null

    override fun setOnMenuItemClickListener(menuItemClickListener: MenuItem.OnMenuItemClickListener?) = this

    override fun getMenuInfo() = null

    override fun setShowAsAction(actionEnum: Int) {
    }

    override fun setShowAsActionFlags(actionEnum: Int) = this

    override fun setActionView(view: View?) = this

    override fun setActionView(resId: Int) = this

    override fun getActionView() = null

    override fun setActionProvider(actionProvider: android.view.ActionProvider?) = this

    override fun getActionProvider() = null

    override fun expandActionView() = false

    override fun collapseActionView() = false

    override fun isActionViewExpanded() = false

    override fun setOnActionExpandListener(listener: MenuItem.OnActionExpandListener?) = this

    override fun setContentDescription(contentDescription: CharSequence?) = this

    override fun getContentDescription() = null

    override fun setTooltipText(tooltipText: CharSequence?) = this

    override fun getTooltipText() = null

    override fun setSupportActionProvider(actionProvider: ActionProvider?) = this

    override fun getSupportActionProvider() = null

    override fun requiresActionButton() = false

    override fun requiresOverflow() = false
}