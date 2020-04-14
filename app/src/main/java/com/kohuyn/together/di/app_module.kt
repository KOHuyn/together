package com.kohuyn.together.di

import androidx.room.Room
import com.google.gson.GsonBuilder
import com.kohuyn.together.R
import com.kohuyn.together.data.AppDataManager
import com.kohuyn.together.data.DataManager
import com.kohuyn.together.data.db.AppDatabase
import com.kohuyn.together.data.db.AppDbHelper
import com.kohuyn.together.data.db.DbHelper
import com.kohuyn.together.data.prefs.AppPrefsHelper
import com.kohuyn.together.data.prefs.PrefsHelper
import com.utils.SchedulerProvider
import org.koin.core.module.Module
import org.koin.dsl.module
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

val appModule :Module = module {
    single { SchedulerProvider() }
    single { AppPrefsHelper(get(),"together",get())  as PrefsHelper}
    single { AppDataManager(get(),get()) as DataManager }
    single { AppDbHelper(get())  as DbHelper}
    single {
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'X'").create()!!
    }
    single {
        CalligraphyConfig.Builder().setDefaultFontPath("fonts/segui/seguisb_regular.ttf")
            .setFontAttrId(R.attr.fontPath)
            .build()
    }
        single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "together.sqlite").build()
    }
}
val togetherModule = listOf(appModule, viewModule)