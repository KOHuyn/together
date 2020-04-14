package com.kohuyn.together.ui.count

import android.os.Bundle
import com.base.BaseFragment
import com.kohuyn.together.BR
import com.kohuyn.together.R
import com.kohuyn.together.data.model.Event
import com.kohuyn.together.databinding.FragmentCountBinding
import com.kohuyn.together.ui.count.adapter.CountAdapter
import com.kohuyn.together.ui.utils.*
import com.kohuyn.together.ui.utils.event.EventChangeDateStart
import com.kohuyn.together.ui.utils.event.EventChangeImage
import org.greenrobot.eventbus.Subscribe
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File
import java.util.*

class CountFragment:BaseFragment<FragmentCountBinding,CountViewModel>(),CountNavigator {


    private val countViewModel by viewModel<CountViewModel>()

    private val countAdapter by lazy { CountAdapter() }

    private var listEvent:MutableList<Event> = mutableListOf()

    override fun getBindingVariable(): Int = BR.countViewModel

    override fun getLayoutId(): Int = R.layout.fragment_count

    override fun getViewModel(): CountViewModel = countViewModel

    override fun updateUI(savedInstanceState: Bundle?) {
        countViewModel.setNavigator(this)
        setViewDate(countViewModel.dateStart!!)
        setImagePicture()
        init()
        setupRCV()
    }

    private fun setViewDate(dateStart:Long){
        if(countViewModel.dateStart != 0L){
        val countDate = DateUtils.countDateStartToNow(dateStart,Calendar.getInstance().timeInMillis)
        binding!!.txtYears.text = countDate.years.toString()
        binding!!.txtMonths.text = countDate.months.toString()
        binding!!.txtDays.text = countDate.days.toString()
        binding!!.txtCountDays.text = countDate.countDay.toString()
        binding!!.txtDateStart.text = countDate.date
        }else{
            binding!!.txtYears.text = "-"
            binding!!.txtMonths.text = "-"
            binding!!.txtDays.text = "-"
            binding!!.txtCountDays.text = "-"
            binding!!.txtDateStart.text = "-/-/--"
        }
    }

    @Subscribe
    fun changeDayStart(eventChangeDateStart: EventChangeDateStart){
        setViewDate(eventChangeDateStart.dateStart)
    }

    private fun setImagePicture(){
        if(countViewModel.pathPictureBoy!!.isNotEmpty()){
            binding!!.imgBoy.setPathLocalImage(countViewModel.pathPictureBoy!!,true)
        }
        if(countViewModel.pathPictureGirl!!.isNotEmpty()){
            binding!!.imgGirl.setPathLocalImage(countViewModel.pathPictureGirl!!,true)
        }
        if(countViewModel.pathPictureBoy!!.isEmpty()){
            binding!!.imgBoy.setDrawableImage(R.drawable.ic_avt_nam)
        }
        if(countViewModel.pathPictureGirl!!.isEmpty()){
            binding!!.imgGirl.setDrawableImage(R.drawable.ic_avt_nu)
        }

    }

    @Subscribe
    fun changeImage(eventChangeImage: EventChangeImage){
        when(eventChangeImage.type){
            TypeImage.BOY -> binding!!.imgBoy.setLocalImage(eventChangeImage.file,true)
            TypeImage.GIRL -> binding!!.imgGirl.setLocalImage(eventChangeImage.file,true)
            TypeImage.BACKGROUND -> toast("background")
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

    private fun setupRCV(){
        setUpRcv(binding!!.rcvEvent,countAdapter, isNestedScrollingEnabled = false)
        countAdapter.listEvent = listEvent
    }

    private fun init(){
        listEvent.add(Event("Valentine Day","14/2/2020","in 1 day"))
        listEvent.add(Event("4 years","12/4/2020","in 58 day"))
        listEvent.add(Event("1500 days","21/5/2020","in 97 day"))
    }
}