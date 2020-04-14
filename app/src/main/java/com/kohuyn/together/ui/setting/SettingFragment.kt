package com.kohuyn.together.ui.setting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.base.BaseFragment
import com.github.dhaval2404.imagepicker.ImagePicker
import com.kohuyn.together.BR
import com.kohuyn.together.R
import com.kohuyn.together.databinding.FragmentSettingBinding
import com.kohuyn.together.ui.setting.daystart.CalendarBottomSheetFragment
import com.kohuyn.together.ui.utils.TypeImage
import com.kohuyn.together.ui.utils.event.EventChangeDateStart
import com.kohuyn.together.ui.utils.event.EventChangeImage
import com.kohuyn.together.ui.utils.postNormal

import org.koin.android.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>(), SettingNavigator {

    companion object {
        private const val BOY_PROFILE_IMAGE_REQ_CODE = 101
        private const val BACKGROUND_IMAGE_REQ_CODE = 102
        private const val GIRL_PROFILE_IMAGE_REQ_CODE = 103
    }

    private val settingViewModel by viewModel<SettingViewModel>()

    lateinit var bottomSheetFragment: CalendarBottomSheetFragment

    override fun getBindingVariable(): Int = BR.settingViewModel

    override fun getLayoutId(): Int = R.layout.fragment_setting

    override fun getViewModel(): SettingViewModel = settingViewModel

    override fun updateUI(savedInstanceState: Bundle?) {
        settingViewModel.setNavigator(this)
    }

    override fun back() {
        onBackPressed()
    }

    override fun setPassword() {
        toast("setPassword")
    }

    override fun changePictureBoy() {
        ImagePicker.with(this)
            .cropSquare()
            .galleryOnly()
            .setImageProviderInterceptor { imageProvider ->
                Log.e("ImagePicker","Selected ImageProvider: "+imageProvider.name)
            }
            .maxResultSize(512,512)
            .start(BOY_PROFILE_IMAGE_REQ_CODE)
    }

    override fun changePictureGirl() {

        ImagePicker.with(this)
            .cropSquare()
            .galleryOnly()
            .setImageProviderInterceptor { imageProvider ->
                Log.e("ImagePicker","Selected ImageProvider: "+imageProvider.name)
            }
            .maxResultSize(512,512)
            .start(GIRL_PROFILE_IMAGE_REQ_CODE)
    }

    override fun changeBackground() {
        ImagePicker.with(this)
            // Crop Image(User can choose Aspect Ratio)
            .crop(9f,16f)
            // User can only select image from Gallery
            .galleryOnly()

            .galleryMimeTypes(  //no gif images at all
                mimeTypes = arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )
            // Image resolution will be less than 1080 x 1920
            .maxResultSize(1080, 1920)
            .start(BACKGROUND_IMAGE_REQ_CODE)
    }

    override fun changeDayStart() {
        bottomSheetFragment = CalendarBottomSheetFragment()
        bottomSheetFragment.show(childFragmentManager, "TAG")
        bottomSheetFragment.pickerDateCallback =
            object : CalendarBottomSheetFragment.PickerDateCallback {
                override fun onClickDate(calendar: Long) {
                    settingViewModel.setDateStart(calendar)
                    postNormal(EventChangeDateStart(calendar))
                    bottomSheetFragment.dismiss()
                }
            }
    }

    override fun rateApp() {
        toast("rateApp")
    }

    override fun shareApp() {
        toast("shareApp")
    }

    override fun feedback() {
        toast("feedback")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                val file = ImagePicker.getFile(data)!!
                val filePath = ImagePicker.getFilePath(data)
                when(requestCode){
                    BOY_PROFILE_IMAGE_REQ_CODE ->{
                        postNormal(EventChangeImage(file,TypeImage.BOY))
                        settingViewModel.setFilePathPictureBoy(filePath!!)
                    }
                    GIRL_PROFILE_IMAGE_REQ_CODE ->{
                        postNormal(EventChangeImage(file,TypeImage.GIRL))
                        settingViewModel.setFilePathPictureGirl(filePath!!)
                    }
                    BACKGROUND_IMAGE_REQ_CODE->{
                        postNormal(EventChangeImage(file,TypeImage.BACKGROUND))
                        settingViewModel.setFilePathBackground(filePath!!)
                    }
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