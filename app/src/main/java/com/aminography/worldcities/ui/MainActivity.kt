package com.aminography.worldcities.ui

import com.aminography.worldcities.databinding.ActivityMainBinding
import com.aminography.worldcities.ui.base.BaseActivity

/**
 * @author aminography
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun inflateBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
}