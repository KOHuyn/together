package com.kohuyn.together.data.db.dao

import androidx.room.*
import com.kohuyn.together.data.model.Memory

@Dao
interface MemoriesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(memory:Memory):Long

    @Query("select * from memory order by dateInMilis desc")
    fun getAllMemories():List<Memory>

    @Delete
    fun deleteMemory(memory: Memory)

    @Update
    fun updateMemory(memory: Memory)
}