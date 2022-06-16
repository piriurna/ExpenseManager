package com.blankwhite.expensemanager.ui.main.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.blankwhite.expensemanager.BaseActivity
import com.blankwhite.expensemanager.R
import com.blankwhite.expensemanager.utils.BackButtonHandlerInterface
import com.blankwhite.expensemanager.utils.OnBackClickListener


/**
 * @param T the binding associated with the fragment
 */
abstract class BaseFragment : Fragment(), OnBackClickListener {

    open fun useDefaultToolbar() : Boolean {
      return true
    }

    fun getMainActivity() : BaseActivity {
        return activity as BaseActivity
    }

    private var backButtonHandler: BackButtonHandlerInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        backButtonHandler = requireActivity() as BackButtonHandlerInterface
        backButtonHandler?.addBackClickListener(this)
    }

    override fun onDetach() {
        super.onDetach()
        backButtonHandler!!.removeBackClickListener(this)
        backButtonHandler = null
    }

    override fun handleBackPressed(): Boolean {
        //This method handle onBackPressed()! return true or false
        return false
    }

    protected abstract fun getBinding(inflater: LayoutInflater, container: ViewGroup?) : ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(!useDefaultToolbar()) getMainActivity().hideToolbar() else getMainActivity().showToolbar()

        return getBinding(inflater, container).root
    }

    protected fun getNavController() : NavController {
        return Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
    }
    protected fun navigateTo(action: Int, bundle: Bundle = Bundle()) {
        getNavController().navigate(action, bundle)
    }

    fun signOut() {
        getMainActivity().signOut()
    }


}