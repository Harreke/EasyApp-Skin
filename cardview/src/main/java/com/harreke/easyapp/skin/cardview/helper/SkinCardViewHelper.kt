package com.harreke.easyapp.skin.cardview.helper

import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.harreke.easyapp.skin.cardview.R
import com.harreke.easyapp.skin.core.SkinResourcesManager

class SkinCardViewHelper(private val view: CardView, attrs: AttributeSet?, private val previewSkinName: String?) {
    private var mCardBackgroundColorId = 0

    init {
        val style = view.context.obtainStyledAttributes(attrs, R.styleable.SkinCardView)
        mCardBackgroundColorId = style.getResourceId(R.styleable.SkinCardView_cardBackgroundColor, 0)
        style.recycle()
    }

    fun setCardBackgroundColorResource(cardBackgroundColorId: Int) {
        mCardBackgroundColorId = cardBackgroundColorId
        updateCardBackgroundId()
    }

    private fun updateCardBackgroundId() {
        if (mCardBackgroundColorId == 0) return
        //        val previewSkinName = if (view.isInEditMode) view.previewSkinName else null
        //        val previewSkinName = view.previewSkinName
        view.setCardBackgroundColor(SkinResourcesManager.getSkinColorStateList(view.context, mCardBackgroundColorId, previewSkinName))
    }

    fun update() {
        updateCardBackgroundId()
    }
}