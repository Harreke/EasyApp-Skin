package com.harreke.easyapp.skin.core

import android.util.AttributeSet
import android.view.View
import com.harreke.easyapp.skin.core.stainer.ISkinStainer

interface ISkinStainerWrapper {
    fun wrapView(view: View, attrs: AttributeSet): ISkinStainer?
}