package com.kohuyn.together.ui.count

import com.kohuyn.together.base.BaseViewModel
import com.kohuyn.together.data.DataManager
import com.utils.SchedulerProvider

class CountViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<CountNavigator>(dataManager, schedulerProvider) {
    val dateStart = dataManager.getDateStart()

    val pathPictureBoy = dataManager.getFilePathPictureBoy()

    val pathPictureGirl = dataManager.getFilePathPictureGirl()
}