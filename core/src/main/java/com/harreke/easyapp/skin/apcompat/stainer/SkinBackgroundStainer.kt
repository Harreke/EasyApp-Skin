package com.harreke.easyapp.skin.apcompat.stainer

import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.harreke.easyapp.skin.apcompat.helper.SkinBackgroundHelper
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.stainer.SkinStainer

@Suppress("LeakingThis")
open class SkinBackgroundStainer(view: View, attrs: AttributeSet) : SkinStainer(view, attrs) {
    val backgroundHelper = SkinBackgroundHelper(view, attrs, previewSkinName)

    fun setBackgroundResource(@DrawableRes backgroundId: Int) {
        backgroundHelper.setBackgroundResource(backgroundId)
    }

    fun setBackgroundTintResource(@ColorRes backgroundTintId: Int) {
        backgroundHelper.setBackgroundTintResource(backgroundTintId)
    }

    override fun updateSkin() {
        backgroundHelper.update()
    }

    companion object {
        val View.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinBackgroundStainer
    }
}