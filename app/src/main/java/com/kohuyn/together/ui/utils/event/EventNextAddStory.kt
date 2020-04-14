package com.kohuyn.together.ui.utils.event

import android.os.Bundle
import com.kohuyn.together.data.model.Memory

class EventNextAddStory {
    var clazz: Class<*>
    var bundle: Bundle? = null
    var isAddToBackStack: Boolean = false

    constructor(clazz: Class<*>, bundle: Bundle, addToBackStack: Boolean) {
        this.clazz = clazz
        this.bundle = bundle
        this.isAddToBackStack = addToBackStack
    }

    constructor(clazz: Class<*>, addToBackStack: Boolean) {
        this.clazz = clazz
        this.isAddToBackStack = addToBackStack
    }

    constructor(clazz: Class<*>) {
        this.clazz = clazz
    }
}