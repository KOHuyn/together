package com.kohuyn.together.data.prefs

interface PrefsHelper{

    fun getDateStart() :Long?

    fun setDateStart(date:Long?)

    fun getFilePathPictureBoy():String?

    fun setFilePathPictureBoy(path:String?)

    fun getFilePathPictureGirl():String?

    fun setFilePathPictureGirl(path:String?)

    fun getFilePathBackground():String?

    fun setFilePathBackground(path:String?)

}