package com.harreke.easyapp.skin.sample

import android.os.Bundle
import android.view.Gravity
import android.widget.CheckBox
import android.widget.Switch
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.tabs.TabLayout
import com.harreke.easyapp.skin.R
import com.harreke.easyapp.skin.cardview.SkinCardViewStainerWrapper
import com.harreke.easyapp.skin.core.SkinBaseActivity
import com.harreke.easyapp.skin.core.SkinManager
import com.harreke.easyapp.skin.material.SkinMaterialStainerWrapper

class MainActivity : SkinBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.dl1)
        val toolbar = findViewById<Toolbar>(R.id.t1)
        toolbar?.setNavigationOnClickListener {
            drawerLayout?.apply {
                if (isDrawerOpen(Gravity.LEFT)) {
                    closeDrawer(Gravity.LEFT)
                } else {
                    openDrawer(Gravity.LEFT)
                }
            }
        }
        findViewById<CheckBox>(R.id.cbNight)?.apply {
            isChecked = SkinManager.skinName == "night"
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    SkinManager.loadSkin(this@MainActivity, "night")
                } else {
                    SkinManager.resetSkin(this@MainActivity)
                }
            }
        }
        findViewById<TabLayout>(R.id.tl1)?.apply {
            addTab(newTab().apply {
                text = "tab1"
            })
            addTab(newTab().apply {
                text = "tab2"
            })
            addTab(newTab().apply {
                text = "tab3"
            })
        }
    }
}