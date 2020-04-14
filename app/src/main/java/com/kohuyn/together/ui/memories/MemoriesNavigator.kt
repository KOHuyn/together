package com.kohuyn.together.ui.memories

import com.kohuyn.together.data.model.Memory

interface MemoriesNavigator {
    fun addStory()

    fun getAllMemories(memories:List<Memory>)
}