package com.kohuyn.together.ui.memories

import com.kohuyn.together.base.BaseViewModel
import com.kohuyn.together.data.DataManager
import com.kohuyn.together.data.model.Memory
import com.utils.SchedulerProvider

class MemoriesViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider):BaseViewModel<MemoriesNavigator>(dataManager, schedulerProvider) {
    fun addStory() = getNavigator().addStory()

    fun getAllMemories() {
        launch {
            dataManager.getAllMemories()
                .compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({
                    getNavigator().getAllMemories(it)
                },{})
        }
    }
    fun deleteMemory(memory:Memory) =
        launch {
        dataManager.deleteMemory(memory)
            .compose(schedulerProvider.ioToMainObservableScheduler())
            .subscribe({},{})
    }
}