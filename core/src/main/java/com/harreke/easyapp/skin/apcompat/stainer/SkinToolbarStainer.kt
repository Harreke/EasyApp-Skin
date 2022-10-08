package com.harreke.easyapp.skin.apcompat.stainer

import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.harreke.easyapp.skin.apcompat.helper.SkinToolbarHelper
import com.harreke.easyapp.skin.core.R

@Suppress("LeakingThis")
open class SkinToolbarStainer(view: Toolbar, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    private val mToolbarHelper = SkinToolbarHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        mToolbarHelper.update()
    }

    companion object {
        val Toolbar.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinToolbarStainer
    }
}