package com.harreke.easyapp.skin.apcompat.helper

import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.ColorRes
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.SkinResourcesManager

class SkinTextViewHelper(private val view: TextView, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mTextColorId = 0
    private var mTextColorHintId = 0
    private var mDrawableLeftId = 0
    private var mDrawableTopId = 0
    private var mDrawableRightId = 0
    private var mDrawableBottomId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinTextView)
        mTextColorId = style.getResourceId(R.styleable.SkinTextView_android_textColor, 0)
        mTextColorHintId = style.getResourceId(R.styleable.SkinTextView_android_textColorHint, 0)
        mDrawableLeftId = style.getResourceId(R.styleable.SkinTextView_android_drawableLeft, 0)
        mDrawableTopId = style.getResourceId(R.styleable.SkinTextView_android_drawableTop, 0)
        mDrawableRightId = style.getResourceId(R.styleable.SkinTextView_android_drawableRight, 0)
        mDrawableBottomId = style.getResourceId(R.styleable.SkinTextView_android_drawableBottom, 0)
        style.recycle()
    }

    fun setTextColorResource(@ColorRes textColorId: Int) {
        mTextColorId = textColorId
        updateTextColor()
    }

    fun setTextColorHintResource(@ColorRes textColorHintId: Int) {
        mTextColorHintId = textColorHintId
        updateTextColorHint()
    }

    fun setDrawableLeftResource(drawableId: Int) {
        mDrawableLeftId = drawableId
        updateDrawableLeft()
    }

    fun setDrawableTopResource(drawableId: Int) {
        mDrawableTopId = drawableId
        updateDrawableTop()
    }

    fun setDrawableRightResource(drawableId: Int) {
        mDrawableRightId = drawableId
        updateDrawableRight()
    }

    fun setDrawableBottomResource(drawableId: Int) {
        mDrawableBottomId = drawableId
        updateDrawableBottom()
    }

    fun setDrawablesResources(drawableLeftId: Int, drawableTopId: Int, drawableRightId: Int, drawableBottomId: Int) {
        mDrawableLeftId = drawableLeftId
        mDrawableTopId = drawableTopId
        mDrawableTopId = drawableRightId
        mDrawableBottomId = drawableBottomId
        updateDrawables()
    }

    private fun updateTextColor() {
        if (mTextColorId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.setTextColor(SkinResourcesManager.getSkinColorStateList(view.context, mTextColorId, previewSkinName))
    }

    private fun updateTextColorHint() {
        if (mTextColorHintId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.setHintTextColor(SkinResourcesManager.getSkinColorStateList(view.context, mTextColorHintId, previewSkinName))
    }

    private fun updateDrawableLeft() {
        if (mDrawableLeftId == 0) return
        val view = view
        val drawableLeft = SkinResourcesManager.getSkinDrawable(view.context, mDrawableLeftId, previewSkinName) ?: return
        drawableLeft.setBounds(0, 0, drawableLeft.intrinsicWidth, drawableLeft.intrinsicHeight)
        val drawables = view.compoundDrawables
        view.setCompoundDrawables(drawableLeft, drawables[1], drawables[2], drawables[3])
    }

    private fun updateDrawableTop() {
        if (mDrawableTopId == 0) return
        val view = view
        val drawableTop = SkinResourcesManager.getSkinDrawable(view.context, mDrawableTopId, previewSkinName) ?: return
        drawableTop.setBounds(0, 0, drawableTop.intrinsicWidth, drawableTop.intrinsicHeight)
        val drawables = view.compoundDrawables
        view.setCompoundDrawables(drawables[0], drawableTop, drawables[2], drawables[3])
    }

    private fun updateDrawableRight() {
        if (mDrawableRightId == 0) return
        val view = view
        val drawableRight = SkinResourcesManager.getSkinDrawable(view.context, mDrawableRightId, previewSkinName) ?: return
        drawableRight.setBounds(0, 0, drawableRight.intrinsicWidth, drawableRight.intrinsicHeight)
        val drawables = view.compoundDrawables
        view.setCompoundDrawables(drawables[0], drawables[1], drawableRight, drawables[3])
    }

    private fun updateDrawableBottom() {
        if (mDrawableBottomId == 0) return
        val view = view
        val drawableBottom = SkinResourcesManager.getSkinDrawable(view.context, mDrawableBottomId, previewSkinName) ?: return
        drawableBottom.setBounds(0, 0, drawableBottom.intrinsicWidth, drawableBottom.intrinsicHeight)
        val drawables = view.compoundDrawables
        view.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawableBottom)
    }

    private fun updateDrawables() {
        val view = view
        val context = view.context
        val previewSkinName = previewSkinName
        val drawables = view.compoundDrawables
        val drawableLeft = if (mDrawableLeftId == 0) drawables[0] else SkinResourcesManager.getSkinDrawable(context, mDrawableLeftId, previewSkinName)
        val drawableTop = if (mDrawableTopId == 0) drawables[1] else SkinResourcesManager.getSkinDrawable(context, mDrawableTopId, previewSkinName)
        val drawableRight = if (mDrawableRightId == 0) drawables[2] else SkinResourcesManager.getSkinDrawable(context, mDrawableRightId, previewSkinName)
        val drawableBottom = if (mDrawableBottomId == 0) drawables[3] else SkinResourcesManager.getSkinDrawable(context, mDrawableBottomId, previewSkinName)
        view.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom)
    }

    fun update() {
        updateTextColor()
        updateTextColorHint()
        updateDrawables()
    }
}