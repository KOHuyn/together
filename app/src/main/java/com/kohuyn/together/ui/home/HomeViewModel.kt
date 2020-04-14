package com.kohuyn.together.ui.home

import com.kohuyn.together.base.BaseViewModel
import com.kohuyn.together.data.DataManager
import com.utils.SchedulerProvider

class HomeViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<HomeNavigator>(dataManager, schedulerProvider) {
}