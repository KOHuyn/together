package com.kohuyn.together.ui.home.homefrag

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.base.BR
import com.base.BaseFragment
import com.event.EventNextFragment
import com.kohuyn.together.R
import com.kohuyn.together.databinding.FragmentHomeBinding
import com.kohuyn.together.ui.count.CountFragment
import com.kohuyn.together.ui.home.homefrag.adapter.HomeViewPagerAdapter
import com.kohuyn.together.ui.memories.MemoriesFragment
import com.kohuyn.together.ui.setting.SettingFragment
import com.kohuyn.together.ui.utils.*
import com.kohuyn.together.ui.utils.event.EventChangeImage
import com.kohuyn.together.ui.utils.event.EventNextSetting
import com.utils.ext.setDrawableBottom
import com.utils.ext.setTextColorz
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment :BaseFragment<FragmentHomeBinding,HomeFragViewModel>(),HomeFragNavigator {
    private val homeFragViewModel by viewModel<HomeFragViewModel>()

    private var PAGE_COUNT = 0

    private val PAGE_MEMORIES = 1

    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun getBindingVariable(): Int = BR.homeFragViewModel

    override fun getLayoutId(): Int= R.layout.fragment_home

    override fun getViewModel(): HomeFragViewModel  = homeFragViewModel

    override fun updateUI(savedInstanceState: Bundle?) {
        homeFragViewModel.setNavigator(this)
        setupViewPager()
        setImageBackground()
    }
    @Subscribe
    fun changeImage(eventChangeImage: EventChangeImage){
        if(eventChangeImage.type==TypeImage.BACKGROUND){
            binding!!.imgBackground.setLocalImage(eventChangeImage.file,false)
        }
    }

    private fun setImageBackground(){
        if(homeFragViewModel.changeBackground!!.isNotEmpty()){
            binding!!.imgBackground.setPathLocalImage(homeFragViewModel.changeBackground!!,false)
        }else{
            binding!!.imgBackground.setDrawableImage(R.drawable.couple_splash)
        }
    }

    override fun onStart() {
        super.onStart()
        register(this)
    }

    override fun onStop() {
        super.onStop()
        unregister(this)
    }
    private fun setupViewPager(){
        val listFragment :ArrayList<Fragment> = arrayListOf(CountFragment(),MemoriesFragment())
        val listTitles = arrayListOf<String>()
        homeViewPagerAdapter = HomeViewPagerAdapter(parentFragmentManager,listFragment,listTitles)
        binding!!.pagerHome.adapter = homeViewPagerAdapter
        binding!!.pagerHome.offscreenPageLimit = 2
        binding!!.pagerHome.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                when(binding!!.pagerHome.currentItem){
                    PAGE_COUNT -> clickCountChangeText()
                    PAGE_MEMORIES->clickMemoriesChangeText()
                }
            }
        })
    }
    override fun openSetting() {
        postNormal(EventNextSetting(SettingFragment::class.java,true))
    }

    override fun openCount() {
       binding!!.pagerHome.currentItem = PAGE_COUNT
        clickCountChangeText()
    }

    override fun openMemories() {
        binding!!.pagerHome.currentItem = PAGE_MEMORIES
        clickMemoriesChangeText()
    }
    private fun clickCountChangeText(){
        binding!!.txtCount.let { textView ->
            textView.textSize = 20f
            textView.setDrawableBottom(R.drawable.ic_dot)
            textView.setTextColorz(R.color.clr_white)
            textView.setTypeface(null,Typeface.BOLD)
        }
        binding!!.txtMemories.let { textView ->
            textView.textSize = 15f
            textView.setDrawableBottom(0)
            textView.setTextColorz(R.color.clr_date)
            textView.setTypeface(null,Typeface.NORMAL)
        }
    }
    private fun clickMemoriesChangeText(){
        binding!!.txtCount.let { textView ->
            textView.textSize = 15f
            textView.setDrawableBottom(0)
            textView.setTextColorz(R.color.clr_date)
            textView.setTypeface(null,Typeface.NORMAL)
        }
        binding!!.txtMemories.let { textView ->
            textView.textSize = 20f
            textView.setDrawableBottom(R.drawable.ic_dot)
            textView.setTextColorz(R.color.clr_white)
            textView.setTypeface(null,Typeface.BOLD)
        }
    }
}