package com.aminography.worldcities.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aminography.worldcities.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author aminography
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}