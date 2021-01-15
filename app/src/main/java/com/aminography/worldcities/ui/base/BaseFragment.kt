package com.aminography.worldcities.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.aminography.worldcities.di.AppComponent
import com.aminography.worldcities.di.appComponent

/**
 * @author aminography
 */
abstract class BaseFragment(
    @LayoutRes private val layoutResId: Int
) : Fragment() {

    protected val appComponent: AppComponent
        get() = requireActivity().appComponent

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