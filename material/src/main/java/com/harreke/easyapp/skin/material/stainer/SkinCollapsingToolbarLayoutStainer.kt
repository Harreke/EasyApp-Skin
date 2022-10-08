package com.harreke.easyapp.skin.material.stainer

import android.util.AttributeSet
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.harreke.easyapp.skin.apcompat.stainer.SkinBackgroundStainer
import com.harreke.easyapp.skin.material.R
import com.harreke.easyapp.skin.material.helper.SkinCollapsingToolbarLayoutHelper

@Suppress("LeakingThis")
class SkinCollapsingToolbarLayoutStainer(view: CollapsingToolbarLayout, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    val collapsingToolbarLayoutHelper = SkinCollapsingToolbarLayoutHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        collapsingToolbarLayoutHelper.update()
    }

    companion object {
        val CollapsingToolbarLayout.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinCollapsingToolbarLayoutStainer
    }
}