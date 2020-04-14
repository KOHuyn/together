package com.kohuyn.together.data.db

import com.kohuyn.together.data.model.Memory
import io.reactivex.Observable

class AppDbHelper constructor(private val appDatabase: AppDatabase):DbHelper{

    override fun saveMemory(memory: Memory): Observable<Long> =
        Observable.fromCallable {
            appDatabase.memoriesDao().save(memory)
        }

    override fun getAllMemories(): Observable<List<Memory>> =
        Observable.fromCallable {
            appDatabase.memoriesDao().getAllMemories()
        }

    override fun deleteMemory(memory: Memory): Observable<Boolean> =
        Observable.fromCallable {
            appDatabase.memoriesDao().deleteMemory(memory)
            true
        }

    override fun updateMemory(memory: Memory): Observable<Boolean> =
        Observable.fromCallable {
            appDatabase.memoriesDao().updateMemory(memory)
            true
        }
}