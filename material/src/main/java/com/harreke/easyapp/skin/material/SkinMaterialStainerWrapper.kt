package com.harreke.easyapp.skin.material

import android.util.AttributeSet
import android.view.View
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout
import com.harreke.easyapp.skin.core.ISkinStainerWrapper
import com.harreke.easyapp.skin.material.stainer.SkinCollapsingToolbarLayoutStainer
import com.harreke.easyapp.skin.material.stainer.SkinFloatingActionButtonStainer
import com.harreke.easyapp.skin.material.stainer.SkinNavigationBarViewStainer
import com.harreke.easyapp.skin.material.stainer.SkinNavigationViewStainer
import com.harreke.easyapp.skin.material.stainer.SkinTabLayoutStainer
import com.harreke.easyapp.skin.material.stainer.SkinTextInputLayoutStainer

class SkinMaterialStainerWrapper : ISkinStainerWrapper {
    override fun wrapView(view: View, attrs: AttributeSet) = when (view) {
        is NavigationView -> SkinNavigationViewStainer(view, attrs)
        is NavigationBarView -> SkinNavigationBarViewStainer(view, attrs)
        is TabLayout -> SkinTabLayoutStainer(view, attrs)
        is CollapsingToolbarLayout -> SkinCollapsingToolbarLayoutStainer(view, attrs)
        is FloatingActionButton -> SkinFloatingActionButtonStainer(view, attrs)
        is TextInputLayout -> SkinTextInputLayoutStainer(view, attrs)
        else -> null
    }
}