package com.kohuyn.together.data

import com.kohuyn.together.data.db.DbHelper
import com.kohuyn.together.data.model.Memory
import com.kohuyn.together.data.prefs.PrefsHelper
import io.reactivex.Observable

class AppDataManager constructor(
    private val prefsHelper: PrefsHelper,
    private val dbHelper: DbHelper
) : DataManager {
    override fun getDateStart(): Long? = prefsHelper.getDateStart()

    override fun setDateStart(date: Long?) = prefsHelper.setDateStart(date)

    override fun getFilePathPictureBoy(): String? = prefsHelper.getFilePathPictureBoy()

    override fun setFilePathPictureBoy(path: String?) = prefsHelper.setFilePathPictureBoy(path)

    override fun getFilePathPictureGirl(): String? = prefsHelper.getFilePathPictureGirl()

    override fun setFilePathPictureGirl(path: String?) = prefsHelper.setFilePathPictureGirl(path)

    override fun getFilePathBackground(): String? = prefsHelper.getFilePathBackground()

    override fun setFilePathBackground(path: String?) = prefsHelper.setFilePathBackground(path)

    override fun saveMemory(memory: Memory): Observable<Long> = dbHelper.saveMemory(memory)

    override fun getAllMemories(): Observable<List<Memory>> = dbHelper.getAllMemories()

    override fun deleteMemory(memory: Memory): Observable<Boolean> = dbHelper.deleteMemory(memory)

    override fun updateMemory(memory: Memory): Observable<Boolean>  = dbHelper.updateMemory(memory)
}