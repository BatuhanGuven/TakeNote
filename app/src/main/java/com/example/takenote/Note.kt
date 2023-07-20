package com.example.takenote

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    val title : String,
    val content : String,
)
