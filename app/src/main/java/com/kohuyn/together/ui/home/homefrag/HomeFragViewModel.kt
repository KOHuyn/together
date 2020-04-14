package com.kohuyn.together.ui.home.homefrag

import com.kohuyn.together.base.BaseViewModel
import com.kohuyn.together.data.DataManager
import com.utils.SchedulerProvider

class HomeFragViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<HomeFragNavigator>(dataManager, schedulerProvider) {
    fun openSetting() = getNavigator().openSetting()

    fun openCount() = getNavigator().openCount()

    fun openMemories() = getNavigator().openMemories()

    val changeBackground = dataManager.getFilePathBackground()
}