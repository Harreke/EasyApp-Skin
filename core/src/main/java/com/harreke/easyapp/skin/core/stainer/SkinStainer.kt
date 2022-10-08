package com.harreke.easyapp.skin.core.stainer

import android.util.AttributeSet
import android.view.View
import com.harreke.easyapp.skin.core.R
import com.harreke.easyapp.skin.core.helper.SkinPreviewHelper

@Suppress("LeakingThis")
abstract class SkinStainer(view: View, attrs: AttributeSet) : ISkinStainer {
    override var previewSkinName = SkinPreviewHelper.parseAttrs(view, attrs)

    init {
        view.setTag(R.id.skin_core_stainer, this)
    }
}