package com.dev_marinov.roomphilippstudentdirector.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject( // предметы
    @PrimaryKey(autoGenerate = false)
    val subjectName: String
)
