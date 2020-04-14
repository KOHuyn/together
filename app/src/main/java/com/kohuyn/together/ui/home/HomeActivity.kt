package com.kohuyn.together.ui.home

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.base.BaseActivity
import com.event.EventNextFragment
import com.kohuyn.together.BR
import com.kohuyn.together.R
import com.kohuyn.together.databinding.ActivityHomeBinding
import com.kohuyn.together.ui.count.CountFragment
import com.kohuyn.together.ui.home.homefrag.HomeFragment
import com.kohuyn.together.ui.setting.SettingFragment
import com.kohuyn.together.ui.utils.event.EventNextAddStory
import com.kohuyn.together.ui.utils.event.EventNextSetting
import com.kohuyn.together.ui.utils.register
import com.kohuyn.together.ui.utils.unregister
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.viewmodel.ext.android.viewModel


class HomeActivity:BaseActivity<ActivityHomeBinding,HomeViewModel>(),HomeNavigator {
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun getBindingVariable(): Int = BR.homeViewModel

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun getViewModel(): HomeViewModel = homeViewModel

    override fun updateUI(savedInstanceState: Bundle?) {
        homeViewModel.setNavigator(this)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        openFragment(R.id.container,HomeFragment::class.java,null,true)
    }
    override fun onStart() {
        super.onStart()
        register(this)
    }

    override fun onStop() {
        super.onStop()
        unregister(this)
    }
    @Subscribe
    fun onOpenSetting(eventOpenSetting:EventNextSetting){
        openFragment(R.id.container,eventOpenSetting.clazz,null,eventOpenSetting.isAddToBackStack,R.anim.exit,R.anim.enter,R.anim.pop_exit,R.anim.pop_enter)
    }
    @Subscribe
    fun onOpenAddStory(eventOpenStory:EventNextAddStory){
        if(eventOpenStory.bundle == null){
            openFragment(R.id.container,eventOpenStory.clazz,null,eventOpenStory.isAddToBackStack,R.anim.enter_right,R.anim.exit_right,R.anim.pop_exit,R.anim.pop_enter)
        }else{
            openFragment(R.id.container,eventOpenStory.clazz,eventOpenStory.bundle,eventOpenStory.isAddToBackStack,R.anim.enter_right,R.anim.exit_right,R.anim.pop_exit,R.anim.pop_enter)
        }
    }
}