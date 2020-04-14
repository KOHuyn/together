package com.kohuyn.together.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kohuyn.together.data.db.dao.MemoriesDAO
import com.kohuyn.together.data.model.Memory

@Database(entities = [Memory::class],exportSchema = false,version = 1)
 abstract class AppDatabase :RoomDatabase(){
    abstract fun memoriesDao():MemoriesDAO
}