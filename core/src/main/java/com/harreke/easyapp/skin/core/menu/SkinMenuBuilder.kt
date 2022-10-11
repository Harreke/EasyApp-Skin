package com.harreke.easyapp.skin.core.menu

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.view.KeyEvent
import android.view.MenuItem
import android.view.SubMenu
import androidx.core.internal.view.SupportMenu

@SuppressLint("RestrictedApi")
open class SkinMenuBuilder : SupportMenu {
    private val mMenuItems = ArrayList<SkinMenuItem>()

    private fun addMenuItem(groupId: Int, itemId: Int): SkinMenuItem {
        val menuItem = SkinMenuItem(groupId, itemId)
        mMenuItems.add(menuItem)
        return menuItem
    }

    override fun add(title: CharSequence?) = addMenuItem(0, 0)

    override fun add(titleRes: Int) = addMenuItem(0, 0)

    override fun add(groupId: Int, itemId: Int, order: Int, title: CharSequence?) = addMenuItem(groupId, itemId)

    override fun add(groupId: Int, itemId: Int, order: Int, titleRes: Int) = addMenuItem(groupId, itemId)

    private fun addSubMenu(groupId: Int, itemId: Int): SubMenu {
        val menuItem = addMenuItem(groupId, itemId)
        val subMenu = SkinSubMenuBuilder(this, menuItem)
        menuItem.subMenu = subMenu
        return subMenu
    }

    override fun addSubMenu(title: CharSequence?) = addSubMenu(0, 0)

    override fun addSubMenu(titleRes: Int) = addSubMenu(0, 0)

    override fun addSubMenu(groupId: Int, itemId: Int, order: Int, title: CharSequence?) = addSubMenu(groupId, itemId)

    override fun addSubMenu(groupId: Int, itemId: Int, order: Int, titleRes: Int) = addSubMenu(groupId, itemId)

    override fun addIntentOptions(groupId: Int, itemId: Int, order: Int, caller: ComponentName?, specifics: Array<out Intent>?, intent: Intent?, flags: Int, outSpecificItems: Array<out MenuItem>?) = 0

    override fun removeItem(id: Int) {
        removeFirstWhen { it.itemId == id }
    }

    override fun removeGroup(groupId: Int) {
        removeFirstWhen { it.groupId == groupId }
    }

    override fun clear() {
        mMenuItems.clear()
    }

    override fun setGroupCheckable(group: Int, checkable: Boolean, exclusive: Boolean) {
    }

    override fun setGroupVisible(group: Int, visible: Boolean) {
    }

    override fun setGroupEnabled(group: Int, enabled: Boolean) {
    }

    override fun hasVisibleItems() = false

    override fun findItem(id: Int) = mMenuItems.firstOrNull { it.itemId == id }

    override fun size() = mMenuItems.size

    override fun getItem(index: Int) = mMenuItems.getOrNull(index)

    override fun close() {
    }

    override fun performShortcut(keyCode: Int, event: KeyEvent?, flags: Int) = false

    override fun isShortcutKey(keyCode: Int, event: KeyEvent?) = false

    override fun performIdentifierAction(id: Int, flags: Int) = false

    override fun setQwertyMode(isQwerty: Boolean) {
    }

    override fun setGroupDividerEnabled(enabled: Boolean) {
    }

    private inline fun removeFirstWhen(predicate: (item: SkinMenuItem) -> Boolean) {
        val iterator = mMenuItems.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (predicate(item)) {
                iterator.remove()
                return
            }
        }
    }
}