package com.kohuyn.together.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import java.io.Serializable

@Entity(tableName = "memory")
data class Memory(
    @Expose val date: String,
    @Expose val image: String,
    @Expose val content: String,
    @PrimaryKey
    @Expose val dateInMilis:Long
)