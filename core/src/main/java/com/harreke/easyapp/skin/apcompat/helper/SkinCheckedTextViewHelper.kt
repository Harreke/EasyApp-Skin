package com.harreke.easyapp.skin.apcompat.helper

import android.util.AttributeSet
import android.widget.CheckedTextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.SkinResourcesManager

class SkinCheckedTextViewHelper(private val view: CheckedTextView, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mCheckMarkId = 0
    private var mCheckMarkTintId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinCheckedTextView)
        mCheckMarkId = style.getResourceId(R.styleable.SkinCheckedTextView_android_checkMark, 0)
        mCheckMarkTintId = style.getResourceId(R.styleable.SkinCheckedTextView_android_checkMarkTint, 0)
        style.recycle()
    }

    fun setCheckMarkResource(@DrawableRes checkMarkId: Int) {
        mCheckMarkId = checkMarkId
        updateCheckMark()
    }

    fun setCheckMarkTintResource(@ColorRes checkMarkTintId: Int) {
        mCheckMarkTintId = checkMarkTintId
        updateCheckMarkTint()
    }

    private fun updateCheckMark() {
        if (mCheckMarkId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.checkMarkDrawable = SkinResourcesManager.getSkinDrawable(view.context, mCheckMarkId, previewSkinName)
    }

    private fun updateCheckMarkTint() {
        if (mCheckMarkTintId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.checkMarkTintList = SkinResourcesManager.getSkinColorStateList(view.context, mCheckMarkTintId, previewSkinName)
    }

    fun update() {
        updateCheckMark()
        updateCheckMarkTint()
    }
}