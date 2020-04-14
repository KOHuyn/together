package com.kohuyn.together.ui.memories.addstory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import com.base.BaseFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.kohuyn.together.BR
import com.kohuyn.together.R
import com.kohuyn.together.data.model.Memory
import com.kohuyn.together.databinding.FragmentAddStoryBinding
import com.kohuyn.together.ui.setting.daystart.CalendarBottomSheetFragment
import com.kohuyn.together.ui.utils.*
import com.kohuyn.together.ui.utils.event.EventSaveMemory
import com.utils.ext.argument
import com.utils.ext.isVisible
import com.utils.ext.setVisibility
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class AddStoryFragment : BaseFragment<FragmentAddStoryBinding, AddStoryViewModel>(),
    AddStoryNavigator {
    companion object {
        const val IMAGE_PICKER_PREVIEW = 1234
    }

    private var filePath: String = ""

    private val m by argument(Key.KEY_PUT_MEMORY,"")

    private val gson by inject<Gson>()

    private var memory:Memory?= null

    private var dateInMilis = 0L

    private var isCancel:Boolean = false

    private val addStoryViewModel by viewModel<AddStoryViewModel>()

    lateinit var bottomSheetFragment: CalendarBottomSheetFragment

    override fun getBindingVariable(): Int = BR.addStoryViewModel

    override fun getLayoutId(): Int = R.layout.fragment_add_story

    override fun getViewModel(): AddStoryViewModel = addStoryViewModel

    override fun updateUI(savedInstanceState: Bundle?) {
        addStoryViewModel.setNavigator(this)
        hideKeyboardOutSide(binding!!.lnParent)
        editStory()
        binding!!.txtCalendar.text = DateUtils.getTimeCurrent("dd/MM/yyyy")
    }

    private fun editStory(){
        if(m.isNotEmpty()){
            memory = gson.fromJson(m,Memory::class.java)
            if(memory?.content?.isNotEmpty()!!){
                binding!!.edtAddStory.text = Editable.Factory.getInstance().newEditable(memory?.content)
            }
            if(memory?.image?.isNotEmpty()!!){
                if(!binding!!.cvPreview.isVisible()){
                    binding!!.cvPreview.setVisibility(true)
                }
                binding!!.imgPreview.setPathLocalImage(memory?.image!!)
                filePath = memory!!.image
            }
        }
    }


    override fun back() {
        onBackPressed()
    }

    override fun pickImage() {
        ImagePicker.with(this)
            .crop()
            .galleryOnly()
            .galleryMimeTypes(
                mimeTypes = arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )
            .maxResultSize(1080, 1920)
            .start(IMAGE_PICKER_PREVIEW)
    }

    override fun save() {
        val calendar = Calendar.getInstance().time
        val dateInMilisEx = Calendar.getInstance().timeInMillis
        val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault())
        var date = sdf.format(calendar)
        if(dateInMilis ==0L){
            dateInMilis = dateInMilisEx
        }else{
           val calendar1 = Date(dateInMilis)
            date = sdf.format(calendar1)
        }
        //setup date and calendar success
        if(filePath.isEmpty()&&binding?.edtAddStory?.text.toString().isEmpty()){
            toast(getString(R.string.str_invalid))
        }else{
            //save or update
            if(isCancel){
                filePath = ""
            }
            if(m.isNotEmpty()){
                addStoryViewModel.editStory(Memory(memory!!.date,filePath,binding!!.edtAddStory.text.toString(),memory!!.dateInMilis))
                postNormal(EventSaveMemory(true))
            }else{
                addStoryViewModel.saveMemory(Memory(date,filePath,binding!!.edtAddStory.text.toString(),dateInMilis))
                postNormal(EventSaveMemory(true))
            }
            hideKeyboardOutSide(binding!!.lnParent)
        }
    }

    override fun imgPreview() {
    }

    override fun cancelImg() {
        binding!!.cvPreview.setVisibility(false)
        isCancel = true
    }

    override fun returnMemories() {
        onBackPressed()
    }

    override fun openCalendar() {
        bottomSheetFragment = CalendarBottomSheetFragment()
        bottomSheetFragment.show(childFragmentManager, "TAG")
        bottomSheetFragment.pickerDateCallback =
            object : CalendarBottomSheetFragment.PickerDateCallback {
                override fun onClickDate(calendar: Long) {
                    dateInMilis = calendar
                    bottomSheetFragment.dismiss()
                    val calendar1 = Date(dateInMilis)
                    val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.getDefault())
                    binding!!.txtCalendar.text =  sdf.format(calendar1)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val file = ImagePicker.getFile(data)!!
                 filePath = ImagePicker.getFilePath(data).toString()
                if (requestCode == IMAGE_PICKER_PREVIEW) {
                    binding!!.imgPreview.setLocalImage(file, false)
                    binding!!.cvPreview.setVisibility(true)
                }
            }
            ImagePicker.RESULT_ERROR -> {
                toast(ImagePicker.getError(data))
            }
            else -> {
                toast("Task Cancelled")
            }
        }
    }
}