package com.aminography.worldcities.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Defines base functionality for activities including common mechanism for view binding.
 *
 * @author aminography
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var _binding: VB? = null

    /**
     * The [ViewBinding] instance for accessing to views.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    protected val binding
        get() = _binding!!

    /**
     * Inflates [ViewBinding] for the activity.
     *
     * @return the [ViewBinding] instance of the layout for this activity.
     */
    abstract fun inflateBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateBinding()
        setContentView(binding.root)
    }
}