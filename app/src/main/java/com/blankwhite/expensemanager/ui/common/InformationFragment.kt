package com.blankwhite.expensemanager.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.databinding.InformationFragmentBinding
import com.blankwhite.expensemanager.ui.main.fragments.BaseFragment

abstract class InformationFragment : BaseFragment() {

    override fun useDefaultToolbar(): Boolean = false

    override fun getStatusBarColor(): StatusBarColor {
        return LightStatusBar(requireContext())
    }

    private lateinit var _binding : InformationFragmentBinding
    private val binding : InformationFragmentBinding
    get() = _binding

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
       _binding = InformationFragmentBinding.inflate(inflater, container, false)
        return _binding
    }

    abstract fun getTitle() : String

    abstract fun getDescription() : String

    abstract fun getButtonText() : String

    abstract fun getButtonCallback() : () -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getMainActivity().changeStatusBarColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            ), true);
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.title.text = getTitle()

        binding.description.text = getDescription()

        binding.sendButton.text = getButtonText()

        binding.sendButton.setOnClickListener {
            getButtonCallback().invoke()
        }

        binding.toolbar.setNavigationOnClickListener {
            getMainActivity().changeStatusBarColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.primary
                ), true);
            getNavController().popBackStack()
        }
    }



}