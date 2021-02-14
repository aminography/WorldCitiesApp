package com.aminography.worldcities.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.aminography.worldcities.databinding.ActivityMainBinding
import com.aminography.worldcities.ui.base.BaseActivity

/**
 * The only activity of the application.
 *
 * @author aminography
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
    }

    override fun inflateBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}