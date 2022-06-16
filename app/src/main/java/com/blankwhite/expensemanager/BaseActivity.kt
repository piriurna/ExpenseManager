package com.blankwhite.expensemanager

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import com.blankwhite.expensemanager.ui.login.LoginRegisterActivity
import com.blankwhite.expensemanager.utils.BackButtonHandlerInterface
import com.blankwhite.expensemanager.utils.OnBackClickListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.ref.WeakReference

abstract class BaseActivity : AppCompatActivity(), BackButtonHandlerInterface {

    lateinit var auth : FirebaseAuth


    fun isUserLoggedIn() : Boolean {
        return this::auth.isInitialized && auth.currentUser != null
    }

    private val backClickListenersList: ArrayList<WeakReference<OnBackClickListener>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
    }

    fun signOut() {
        auth.signOut()
        goToLogin()
    }

    protected fun goToMainActivity() {
        finish()
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(mainActivityIntent)
    }

    protected fun goToLogin() {
        val loginActivity = Intent(this, LoginRegisterActivity::class.java)
        finish()
        startActivity(loginActivity)
    }

    fun refreshUser() {
        if(auth.currentUser == null) {
            goToLogin()
        }else{
            goToMainActivity()

        }
    }

    fun hideToolbar() {
        supportActionBar?.hide();
    }

    fun showToolbar() {
        supportActionBar?.show()
    }

    fun changeStatusBarColor(color: Int, isLight: Boolean) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
    }

    override fun onBackPressed() {
        if (!fragmentsBackKeyIntercept()) {
            super.onBackPressed()
        }
    }

    private fun fragmentsBackKeyIntercept(): Boolean {
        var isIntercept = false
        for (weakRef in backClickListenersList) {
            val onBackClickListener = weakRef.get()
            if (onBackClickListener != null) {
                val isFragmentIntercept: Boolean = onBackClickListener.handleBackPressed()
                if (!isIntercept) isIntercept = isFragmentIntercept
            }
        }
        return isIntercept
    }

    override fun addBackClickListener(onBackClickListener: OnBackClickListener) {
        backClickListenersList.add(WeakReference(onBackClickListener))
    }

    override fun removeBackClickListener(onBackClickListener: OnBackClickListener) {
        val toDeleteListener = backClickListenersList.find { it.get() == onBackClickListener }

        backClickListenersList.remove(toDeleteListener)
    }
}