package com.harreke.easyapp.skin.core

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.LruCache
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.core.content.res.ResourcesCompat
import java.util.*

object SkinResourcesManager {
    internal var skinName = ""
        set(value) {
            field = value
            synchronized(mColorCache) {
                mColorCache.clear()
            }
            synchronized(mColorStateListCache) {
                mColorStateListCache.clear()
            }
            synchronized(mDrawableCache) {
                mDrawableCache.clear()
            }
        }
    private val mResourceIdNames = LruCache<Int, String>(1000)
    private val mColorCache = WeakHashMap<String, Int>(1000)
    private val mColorStateListCache = WeakHashMap<String, ColorStateList>(1000)
    private val mDrawableCache = WeakHashMap<String, Drawable>(1000)

    private fun ensureResourceName(resources: Resources, @DrawableRes resourceId: Int): String? {
        val map = mResourceIdNames
        var resourceName = map.get(resourceId)
        if (resourceName == null) {
            resourceName = resources.getResourceName(resourceId) ?: return null
            map.put(resourceId, resourceName)
        }
        return resourceName
    }

    fun getSkinResourceName(context: Context, resourceId: Int, skinName: String? = null): String? {
        if (resourceId == 0) return null
        val resources = context.resources
        val resourceName = ensureResourceName(resources, resourceId) ?: return null
        val actualSkinName = skinName ?: this.skinName
        return if (actualSkinName.isEmpty()) resourceName else "${resourceName}_$actualSkinName"
    }

    fun getSkinResourceId(context: Context, resourceId: Int, resourceType: String, skinName: String? = null): Int {
        val skinResourceName = getSkinResourceName(context, resourceId, skinName) ?: return 0
        var skinResourceId = context.resources.getIdentifier(skinResourceName, resourceType, null)
        if (skinResourceId == 0) {
            skinResourceId = resourceId
        }
        return skinResourceId
    }

    fun getSkinTextAppearanceColor(context: Context, @StyleRes styleId: Int, skinName: String? = null): Int {
        if (styleId == 0) return Color.TRANSPARENT
        val style = context.obtainStyledAttributes(styleId, androidx.appcompat.R.styleable.TextAppearance)
        val colorId = style.getResourceId(androidx.appcompat.R.styleable.TextAppearance_android_textColor, 0)
        style.recycle()
        return getSkinColor(context, colorId, skinName)
    }

    fun getSkinTextAppearanceColorStateList(context: Context, @StyleRes styleId: Int, skinName: String? = null): ColorStateList? {
        if (styleId == 0) return null
        val style = context.obtainStyledAttributes(styleId, androidx.appcompat.R.styleable.TextAppearance)
        val colorId = style.getResourceId(androidx.appcompat.R.styleable.TextAppearance_android_textColor, 0)
        style.recycle()
        return getSkinColorStateList(context, colorId, skinName)
    }

    fun getSkinColor(context: Context, @ColorRes colorId: Int, skinName: String? = null): Int {
        val skinColorName = getSkinResourceName(context, colorId, skinName) ?: return Color.TRANSPARENT
        val resources = context.resources
        var skinColor = mColorCache[skinColorName]
        if (skinColor == null) {
            var skinColorId = resources.getIdentifier(skinColorName, "color", null)
            if (skinColorId == 0) {
                skinColorId = colorId
            }
            skinColor = try {
                ResourcesCompat.getColor(resources, skinColorId, context.theme)
            } catch (e: Resources.NotFoundException) {
                null
            }
            mColorCache[skinColorName] = skinColor
        }
        return skinColor ?: Color.TRANSPARENT
    }

    fun getSkinColorStateList(context: Context, @ColorRes colorId: Int, skinName: String? = null): ColorStateList? {
        val skinColorStateListName = getSkinResourceName(context, colorId, skinName) ?: return null
        val resources = context.resources
        var skinColorStateList = mColorStateListCache[skinColorStateListName]
        if (skinColorStateList == null) {
            var skinColorStateListId = resources.getIdentifier(skinColorStateListName, "color", null)
            if (skinColorStateListId == 0) {
                skinColorStateListId = colorId
            }
            skinColorStateList = try {
                ResourcesCompat.getColorStateList(resources, skinColorStateListId, context.theme)
            } catch (e: Resources.NotFoundException) {
                null
            }
            mColorStateListCache[skinColorStateListName] = skinColorStateList
        }
        return skinColorStateList
    }

    fun getSkinDrawable(context: Context, @DrawableRes drawableId: Int, skinName: String? = null): Drawable? {
        val skinDrawableName = getSkinResourceName(context, drawableId, skinName) ?: return null
        val resources = context.resources
        var skinDrawable = mDrawableCache[skinDrawableName]
        if (skinDrawable == null) {
            var skinDrawableId = resources.getIdentifier(skinDrawableName, "drawable", null)
            if (skinDrawableId == 0) {
                skinDrawableId = drawableId
            }
            skinDrawable = try {
                ResourcesCompat.getDrawable(resources, skinDrawableId, context.theme)
            } catch (e: Resources.NotFoundException) {
                null
            }
            mDrawableCache[skinDrawableName] = skinDrawable
        }
        return skinDrawable
    }
}