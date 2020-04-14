package com.kohuyn.together.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class AppPrefsHelper constructor(private val context:Context,prefsName:String,val gson:Gson) :PrefsHelper{
    companion object{
        const val DATE_START = "DATE_START"
        const val KEY_PICTURE_BOY = "KEY_PICTURE_BOY"
        const val KEY_PICTURE_GIRL = "KEY_PICTURE_GIRL"
        const val KEY_BACKGROUND = "KEY_BACKGROUND"
    }
    private val sharedPreferences:SharedPreferences = context.getSharedPreferences(prefsName,Context.MODE_PRIVATE)

    override fun getDateStart(): Long? = sharedPreferences.getLong(DATE_START,0L)

    override fun setDateStart(date: Long?) =sharedPreferences.edit().putLong(DATE_START,date!!).apply()

    override fun getFilePathPictureBoy(): String? = sharedPreferences.getString(KEY_PICTURE_BOY,"")

    override fun setFilePathPictureBoy(path: String?) =sharedPreferences.edit().putString(KEY_PICTURE_BOY,path).apply()

    override fun getFilePathPictureGirl(): String? = sharedPreferences.getString(KEY_PICTURE_GIRL,"")

    override fun setFilePathPictureGirl(path: String?) =sharedPreferences.edit().putString(KEY_PICTURE_GIRL,path).apply()

    override fun getFilePathBackground(): String? = sharedPreferences.getString(KEY_BACKGROUND,"")

    override fun setFilePathBackground(path: String?)=sharedPreferences.edit().putString(KEY_BACKGROUND,path).apply()


}