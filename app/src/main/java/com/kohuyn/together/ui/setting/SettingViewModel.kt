package com.kohuyn.together.ui.setting

import com.kohuyn.together.base.BaseViewModel
import com.kohuyn.together.data.DataManager
import com.utils.SchedulerProvider

class SettingViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<SettingNavigator>(dataManager, schedulerProvider) {
    fun back() = getNavigator().back()

    fun setPassword() = getNavigator().setPassword()

    fun changePictureBoy() = getNavigator().changePictureBoy()

    fun changePictureGirl() = getNavigator().changePictureGirl()

    fun changeBackground() = getNavigator().changeBackground()

    fun changeDayStart() = getNavigator().changeDayStart()

    fun rateApp() = getNavigator().rateApp()

    fun shareApp() = getNavigator().shareApp()

    fun setDateStart(date:Long) = dataManager.setDateStart(date)

    fun feedback() = getNavigator().feedback()

    fun setFilePathPictureBoy(path: String) = dataManager.setFilePathPictureBoy(path)

    fun setFilePathPictureGirl(path: String) = dataManager.setFilePathPictureGirl(path)

    fun setFilePathBackground(path: String) = dataManager.setFilePathBackground(path)
}