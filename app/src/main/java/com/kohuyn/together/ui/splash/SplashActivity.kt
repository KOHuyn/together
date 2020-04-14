package com.kohuyn.together.ui.splash

import android.os.Bundle
import com.base.BaseActivity
import com.kohuyn.together.BR
import com.kohuyn.together.R
import com.kohuyn.together.databinding.ActivitySplashBinding
import com.kohuyn.together.ui.home.HomeActivity
import com.kohuyn.together.ui.lockscreen.LockScreenActivity
import com.utils.ext.startActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity:BaseActivity<ActivitySplashBinding,SplashViewModel>() ,SplashNavigator{
    private val splashViewModel by viewModel<SplashViewModel>()

    override fun getBindingVariable(): Int = BR.splashViewModel

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun getViewModel(): SplashViewModel = splashViewModel

    override fun updateUI(savedInstanceState: Bundle?) {
       splashViewModel.setNavigator(this)
        startActivity(HomeActivity::class.java)
//        startActivity(LockScreenActivity::class.java)
        finish()
    }
}