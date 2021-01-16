package com.aminography.worldcities.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * @author aminography
 */
abstract class BaseFragment(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    protected lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId, container, false).also { rootView = it }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitViews(view, savedInstanceState)
    }

    /**
     * Called when the view hierarchy is created.
     */
    abstract fun onInitViews(rootView: View, savedInstanceState: Bundle?)
}