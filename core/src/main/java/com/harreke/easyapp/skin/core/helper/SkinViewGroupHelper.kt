package com.harreke.easyapp.skin.core.helper

import android.view.ViewGroup
import com.harreke.easyapp.skin.core.stainer.ISkinStainer

object SkinViewGroupHelper {
    fun <T> onFinishInflate(view: T) where T : ViewGroup, T : ISkinStainer {
//        if (!view.isInEditMode) return
        val previewSkinName = view.previewSkinName
        if (previewSkinName == null) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i) as? ISkinStainer ?: continue
                child.updateSkin()
            }
        } else {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i) as? ISkinStainer ?: continue
                if (child.previewSkinName == null) {
                    child.previewSkinName = previewSkinName
                }
                child.updateSkin()
            }
        }
    }
}