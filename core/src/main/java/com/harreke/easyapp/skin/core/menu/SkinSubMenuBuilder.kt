package com.harreke.easyapp.skin.core.menu

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.SubMenu
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.internal.view.SupportSubMenu

@SuppressLint("RestrictedApi")
class SkinSubMenuBuilder(private val mParentMenu: SkinMenuBuilder, private val mParentItem: SkinMenuItem) : SkinMenuBuilder(), SupportSubMenu {
    @DrawableRes
    var headerIconId = 0

    @DrawableRes
    var iconId = 0

    override fun getItem() = mParentItem

    override fun setHeaderTitle(titleRes: Int) = this

    override fun setHeaderTitle(title: CharSequence?) = this

    override fun setHeaderIcon(iconRes: Int): SkinSubMenuBuilder {
        headerIconId = iconRes
        return this
    }

    override fun setHeaderIcon(icon: Drawable?): SkinSubMenuBuilder {
        headerIconId = 0
        return this
    }

    override fun setHeaderView(view: View?) = this

    override fun clearHeader() {
    }

    override fun setIcon(iconRes: Int): SubMenu {
        iconId = iconRes
        return this
    }

    override fun setIcon(icon: Drawable?): SubMenu {
        iconId = 0
        return this
    }
}