package com.kohuyn.together.ui.lockscreen

import android.os.Bundle
import android.view.WindowManager
import com.base.BaseActivity
import com.kohuyn.together.BR
import com.kohuyn.together.R
import com.kohuyn.together.databinding.ActivityLockScreenBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LockScreenActivity:BaseActivity<ActivityLockScreenBinding,LockScreenViewModel>(),LockScreenNavigator {
    private val lockScreenViewModel by viewModel<LockScreenViewModel>()

    override fun getBindingVariable(): Int = BR.lockScreenViewModel

    override fun getLayoutId(): Int = R.layout.activity_lock_screen

    override fun getViewModel(): LockScreenViewModel = lockScreenViewModel

    override fun updateUI(savedInstanceState: Bundle?) {
        lockScreenViewModel.setNavigator(this)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}