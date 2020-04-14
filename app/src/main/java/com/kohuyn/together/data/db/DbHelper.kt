package com.kohuyn.together.data.db

import com.kohuyn.together.data.model.Memory
import io.reactivex.Observable

interface DbHelper {
    fun saveMemory(memory: Memory): Observable<Long>

    fun getAllMemories():Observable<List<Memory>>

    fun deleteMemory(memory: Memory):Observable<Boolean>

    fun updateMemory(memory: Memory):Observable<Boolean>
}