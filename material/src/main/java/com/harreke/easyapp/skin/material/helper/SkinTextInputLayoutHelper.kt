package com.harreke.easyapp.skin.material.helper

import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.harreke.easyapp.skin.core.SkinResourcesManager
import com.harreke.easyapp.skin.material.R

@Suppress("DEPRECATION")
class SkinTextInputLayoutHelper(private val view: TextInputLayout, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mHintTextColorId = 0
    private var mHelperTextTextColorId = 0
    private var mErrorTextColorId = 0
    private var mErrorIconDrawableId = 0
    private var mErrorIconTintId = 0
    private var mCounterTextColorId = 0
    private var mPlaceholderTextColorId = 0
    private var mPrefixTextColorId = 0
    private var mSuffixTextColorId = 0
    private var mStartIconDrawableId = 0
    private var mStartIconTintId = 0
    private var mEndIconDrawableId = 0
    private var mEndIconTintId = 0
    private var mBoxStrokeColorId = 0
    private var mBoxStrokeErrorColorId = 0
    private var mBoxBackgroundColorId = 0
    private var mPasswordToggleDrawableId = 0
    private var mPasswordToggleTintId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinTextInputLayout)
        mHintTextColorId = style.getResourceId(R.styleable.SkinTextInputLayout_hintTextColor, 0)
        mHelperTextTextColorId = style.getResourceId(R.styleable.SkinTextInputLayout_helperTextTextColor, 0)
        mErrorTextColorId = style.getResourceId(R.styleable.SkinTextInputLayout_errorTextColor, 0)
        mErrorIconDrawableId = style.getResourceId(R.styleable.SkinTextInputLayout_errorIconDrawable, 0)
        mErrorIconTintId = style.getResourceId(R.styleable.SkinTextInputLayout_errorIconTint, 0)
        mCounterTextColorId = style.getResourceId(R.styleable.SkinTextInputLayout_counterTextColor, 0)
        mPlaceholderTextColorId = style.getResourceId(R.styleable.SkinTextInputLayout_placeholderTextColor, 0)
        mPrefixTextColorId = style.getResourceId(R.styleable.SkinTextInputLayout_prefixTextColor, 0)
        mSuffixTextColorId = style.getResourceId(R.styleable.SkinTextInputLayout_suffixTextColor, 0)
        mStartIconDrawableId = style.getResourceId(R.styleable.SkinTextInputLayout_startIconDrawable, 0)
        mStartIconTintId = style.getResourceId(R.styleable.SkinTextInputLayout_startIconTint, 0)
        mEndIconDrawableId = style.getResourceId(R.styleable.SkinTextInputLayout_endIconDrawable, 0)
        mEndIconTintId = style.getResourceId(R.styleable.SkinTextInputLayout_endIconTint, 0)
        mBoxStrokeColorId = style.getResourceId(R.styleable.SkinTextInputLayout_boxStrokeColor, 0)
        mBoxStrokeErrorColorId = style.getResourceId(R.styleable.SkinTextInputLayout_boxStrokeErrorColor, 0)
        mBoxBackgroundColorId = style.getResourceId(R.styleable.SkinTextInputLayout_boxBackgroundColor, 0)
        mPasswordToggleDrawableId = style.getResourceId(R.styleable.SkinTextInputLayout_passwordToggleDrawable, 0)
        mPasswordToggleTintId = style.getResourceId(R.styleable.SkinTextInputLayout_passwordToggleTint, 0)
        style.recycle()
    }

    fun setHintTextColorResource(hintTextColorId: Int) {
        mHintTextColorId = hintTextColorId
        updateHintTextColor()
    }

    fun setHelperTextTextColorResource(helperTextTextColorId: Int) {
        mHelperTextTextColorId = helperTextTextColorId
        updateHelperTextTextColor()
    }

    fun setErrorTextColorResource(errorTextColorId: Int) {
        mErrorTextColorId = errorTextColorId
        updateErrorTextColor()
    }

    fun setErrorIconDrawableResource(errorIconDrawableId: Int) {
        mErrorIconDrawableId = errorIconDrawableId
        updateErrorIconDrawable()
    }

    fun setErrorIconTintResource(errorIconTintId: Int) {
        mErrorIconTintId = errorIconTintId
        updateErrorIconTint()
    }

    fun seCounterTextColorResource(counterTextColorId: Int) {
        mCounterTextColorId = counterTextColorId
        updateCounterTextColor()
    }

    fun setPlaceholderTextColorResource(placeholderTextColorId: Int) {
        mPlaceholderTextColorId = placeholderTextColorId
        updatePlaceholderTextColor()
    }

    fun setPrefixTextColorResource(prefixTextColorId: Int) {
        mPrefixTextColorId = prefixTextColorId
        updatePrefixTextColor()
    }

    fun setSuffixTextColorResource(SuffixTextColorId: Int) {
        mSuffixTextColorId = SuffixTextColorId
        updateSuffixTextColor()
    }

    fun setStartIconDrawableResource(startIconDrawableId: Int) {
        mStartIconDrawableId = startIconDrawableId
        updateStartIconDrawable()
    }

    fun setStartIconTintResource(startIconTintId: Int) {
        mStartIconTintId = startIconTintId
        updateStartIconTint()
    }

    fun setEndIconDrawableResource(endIconDrawableId: Int) {
        mEndIconDrawableId = endIconDrawableId
        updateEndIconDrawable()
    }

    fun setEndIconTintResource(endIconTintId: Int) {
        mEndIconTintId = endIconTintId
        updateEndIconTint()
    }

    fun setBoxStrokeColorResource(boxStrokeColorId: Int) {
        mBoxStrokeColorId = boxStrokeColorId
        updateBoxStrokeColor()
    }

    fun setBoxStrokeErrorColorResource(boxStrokeErrorColorId: Int) {
        mBoxStrokeErrorColorId = boxStrokeErrorColorId
        updateBoxStrokeErrorColor()
    }

    fun setBoxBackgroundColorResource(boxBackgroundColorId: Int) {
        mBoxBackgroundColorId = boxBackgroundColorId
        updateBoxBackgroundColor()
    }

    fun setPasswordToggleDrawableResource(passwordToggleDrawableId: Int) {
        mPasswordToggleDrawableId = passwordToggleDrawableId
        updatePasswordToggleDrawable()
    }

    fun setPasswordToggleTintResource(passwordToggleTintId: Int) {
        mPasswordToggleTintId = passwordToggleTintId
        updatePasswordToggleTint()
    }

    private fun updateHintTextColor() {
        if (mHintTextColorId == 0) return
        view.hintTextColor = SkinResourcesManager.getSkinColorStateList(view.context, mHintTextColorId, previewSkinName)
    }

    private fun updateHelperTextTextColor() {
        if (mHelperTextTextColorId == 0) return
        view.setHelperTextColor(SkinResourcesManager.getSkinColorStateList(view.context, mHelperTextTextColorId, previewSkinName))
    }

    private fun updateErrorTextColor() {
        if (mErrorTextColorId == 0) return
        view.setErrorTextColor(SkinResourcesManager.getSkinColorStateList(view.context, mErrorTextColorId, previewSkinName))
    }

    private fun updateErrorIconDrawable() {
        if (mErrorIconDrawableId == 0) return
        view.errorIconDrawable = SkinResourcesManager.getSkinDrawable(view.context, mErrorIconDrawableId, previewSkinName)
    }

    private fun updateErrorIconTint() {
        if (mErrorIconTintId == 0) return
        view.setErrorIconTintList(SkinResourcesManager.getSkinColorStateList(view.context, mErrorIconTintId, previewSkinName))
    }

    private fun updateCounterTextColor() {
        if (mCounterTextColorId == 0) return
        view.counterTextColor = SkinResourcesManager.getSkinColorStateList(view.context, mCounterTextColorId, previewSkinName)
    }

    private fun updatePlaceholderTextColor() {
        if (mPlaceholderTextColorId == 0) return
        view.placeholderTextColor = SkinResourcesManager.getSkinColorStateList(view.context, mPlaceholderTextColorId, previewSkinName)
    }

    private fun updatePrefixTextColor() {
        if (mPrefixTextColorId == 0) return
        view.setPrefixTextColor(SkinResourcesManager.getSkinColorStateList(view.context, mPrefixTextColorId, previewSkinName) ?: return)
    }

    private fun updateSuffixTextColor() {
        if (mSuffixTextColorId == 0) return
        view.setSuffixTextColor(SkinResourcesManager.getSkinColorStateList(view.context, mSuffixTextColorId, previewSkinName) ?: return)
    }

    private fun updateStartIconDrawable() {
        if (mStartIconDrawableId == 0) return
        view.startIconDrawable = SkinResourcesManager.getSkinDrawable(view.context, mStartIconDrawableId, previewSkinName)
    }

    private fun updateStartIconTint() {
        if (mStartIconTintId == 0) return
        view.setStartIconTintList(SkinResourcesManager.getSkinColorStateList(view.context, mStartIconTintId, previewSkinName))
    }

    private fun updateEndIconDrawable() {
        if (mEndIconDrawableId == 0) return
        view.endIconDrawable = SkinResourcesManager.getSkinDrawable(view.context, mEndIconDrawableId, previewSkinName)
    }

    private fun updateEndIconTint() {
        if (mEndIconTintId == 0) return
        view.setEndIconTintList(SkinResourcesManager.getSkinColorStateList(view.context, mEndIconTintId, previewSkinName))
    }

    private fun updateBoxStrokeColor() {
        if (mBoxStrokeColorId == 0) return
        view.setBoxStrokeColorStateList(SkinResourcesManager.getSkinColorStateList(view.context, mBoxStrokeColorId, previewSkinName) ?: return)
    }

    private fun updateBoxStrokeErrorColor() {
        if (mBoxStrokeErrorColorId == 0) return
        view.boxStrokeErrorColor = SkinResourcesManager.getSkinColorStateList(view.context, mBoxStrokeErrorColorId, previewSkinName)
    }

    private fun updateBoxBackgroundColor() {
        if (mBoxBackgroundColorId == 0) return
        view.setBoxBackgroundColorStateList(SkinResourcesManager.getSkinColorStateList(view.context, mBoxBackgroundColorId, previewSkinName) ?: return)
    }

    private fun updatePasswordToggleDrawable() {
        if (mPasswordToggleDrawableId == 0) return
        view.passwordVisibilityToggleDrawable = SkinResourcesManager.getSkinDrawable(view.context, mPasswordToggleDrawableId, previewSkinName)
    }

    private fun updatePasswordToggleTint() {
        if (mPasswordToggleTintId == 0) return
        view.setPasswordVisibilityToggleTintList(SkinResourcesManager.getSkinColorStateList(view.context, mPasswordToggleTintId, previewSkinName))
    }

    fun update() {
        updateHintTextColor()
        updateHelperTextTextColor()
        updateErrorTextColor()
        updateCounterTextColor()
        updatePlaceholderTextColor()
        updatePrefixTextColor()
        updateSuffixTextColor()
        updateStartIconDrawable()
        updateStartIconTint()
        updateEndIconDrawable()
        updateEndIconTint()
        updateBoxStrokeColor()
        updateBoxStrokeErrorColor()
        updateBoxBackgroundColor()
        updatePasswordToggleDrawable()
        updatePasswordToggleTint()
    }
}