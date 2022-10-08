package com.harreke.easyapp.skin.apcompat.stainer

import android.util.AttributeSet
import android.widget.SeekBar
import com.harreke.easyapp.skin.apcompat.helper.SkinSeekBarHelper
import com.harreke.easyapp.skin.core.R

@Suppress("LeakingThis")
open class SkinSeekBarStainer(view: SeekBar, attrs: AttributeSet) : SkinProgressBarStainer(view, attrs) {
    private val mSeekBarHelper = SkinSeekBarHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        mSeekBarHelper.update()
    }

    companion object {
        val SeekBar.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinSeekBarStainer
    }
}