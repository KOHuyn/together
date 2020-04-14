package com.kohuyn.together.base

import com.base.ViewModelB
import com.kohuyn.together.data.DataManager
import com.utils.SchedulerProvider

open class BaseViewModel<N>(var dataManager: DataManager,var schedulerProvider: SchedulerProvider):ViewModelB<N>(schedulerProvider) {
}