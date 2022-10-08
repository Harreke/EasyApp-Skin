package com.harreke.easyapp.skin.material

import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.harreke.easyapp.skin.core.ISkinStainerWrapper
import com.harreke.easyapp.skin.material.stainer.SkinCardViewStainer

class SkinCardViewStainerWrapper : ISkinStainerWrapper {
    override fun wrapView(view: View, attrs: AttributeSet) = when (view) {
        is CardView -> SkinCardViewStainer(view, attrs)
        else -> null
    }
}