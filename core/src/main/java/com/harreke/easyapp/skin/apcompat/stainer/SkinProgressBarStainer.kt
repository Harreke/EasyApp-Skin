package com.harreke.easyapp.skin.apcompat.stainer

import android.util.AttributeSet
import android.widget.ProgressBar
import com.harreke.easyapp.skin.apcompat.helper.SkinProgressBarHelper
import com.harreke.easyapp.skin.core.R

@Suppress("LeakingThis")
open class SkinProgressBarStainer(view: ProgressBar, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    val progressBarHelper = SkinProgressBarHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        progressBarHelper.update()
    }

    companion object {
        val ProgressBar.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinProgressBarStainer
    }
}