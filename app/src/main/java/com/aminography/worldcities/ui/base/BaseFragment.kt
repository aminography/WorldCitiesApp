package com.aminography.worldcities.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Defines base functionality for fragments including common mechanism for view binding.
 *
 * @param VB the type of the [ViewBinding] class corresponding to the layout of this fragment.
 *
 * @author aminography
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null

    /**
     * The [ViewBinding] instance for accessing to views.
     */
    protected val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflateBinding(inflater, container).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitViews(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Inflates [ViewBinding] for the fragment.
     *
     * @param inflater an instance of [LayoutInflater].
     * @param container the parent [ViewGroup] to to attach the inflated view to it.
     *
     * @return the [ViewBinding] instance of the layout for this fragment.
     */
    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    /**
     * Called when the view hierarchy is created.
     *
     * @param rootView the root view of the fragment.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     * saved state as given here.
     */
    abstract fun onInitViews(rootView: View, savedInstanceState: Bundle?)
}