package com.harreke.easyapp.skin.material.stainer

import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.harreke.easyapp.skin.apcompat.stainer.SkinBackgroundStainer
import com.harreke.easyapp.skin.cardview.R
import com.harreke.easyapp.skin.cardview.helper.SkinCardViewHelper

@Suppress("LeakingThis")
open class SkinCardViewStainer(view: CardView, attrs: AttributeSet) : SkinBackgroundStainer(view, attrs) {
    val cardViewHelper = SkinCardViewHelper(view, attrs, previewSkinName)

    override fun updateSkin() {
        super.updateSkin()
        cardViewHelper.update()
    }

    companion object {
        val CardView.skinStainer
            get() = getTag(R.id.skin_core_stainer) as? SkinCardViewStainer
    }
}