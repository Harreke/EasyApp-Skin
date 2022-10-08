package com.harreke.easyapp.skin.material.stainer

import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout
import com.harreke.easyapp.skin.apcompat.stainer.SkinBackgroundStainer
import com.harreke.easyapp.skin.material.R
import com.harreke.easyapp.skin.material.helper.SkinTabLayoutHelper

@Suppress("LeakingThis")
class SkinTabLayoutStainer(view: TabLayout, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    val tabLayoutHelper = SkinTabLayoutHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        tabLayoutHelper.update()
    }

    companion object {
        val TabLayout.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinTabLayoutStainer
    }
}