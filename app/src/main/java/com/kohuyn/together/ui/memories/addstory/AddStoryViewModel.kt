package com.kohuyn.together.ui.memories.addstory

import com.kohuyn.together.base.BaseViewModel
import com.kohuyn.together.data.DataManager
import com.kohuyn.together.data.model.Memory
import com.utils.SchedulerProvider

class AddStoryViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
    BaseViewModel<AddStoryNavigator>(dataManager, schedulerProvider) {
    fun back() = getNavigator().back()

    fun pickImage() = getNavigator().pickImage()

    fun save() = getNavigator().save()

    fun imgPreview() = getNavigator().imgPreview()

    fun cancelImg() = getNavigator().cancelImg()

    fun openCalendar() = getNavigator().openCalendar()

    fun saveMemory(memory: Memory) {
        launch {
            dataManager.saveMemory(memory)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({
                    getNavigator().returnMemories()
                }, {})
        }
    }

    fun editStory(memory: Memory) {
        launch {
            dataManager.updateMemory(memory)
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({
                    if (it) {
                        getNavigator().returnMemories()
                    }
                }, { it.printStackTrace() })
        }
    }
}