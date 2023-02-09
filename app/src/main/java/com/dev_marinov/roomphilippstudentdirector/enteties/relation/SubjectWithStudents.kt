package com.dev_marinov.roomphilippstudentdirector.enteties.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.dev_marinov.roomphilippstudentdirector.enteties.Student
import com.dev_marinov.roomphilippstudentdirector.enteties.Subject

// вспомогательный класс который имеет доступ к занятию, по которому можно определить
// студентов, которые его посещают
data class SubjectWithStudents(
    @Embedded
    val subject: Subject,
    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        // сообщаем руму какая таблица определяет это отношение
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val students: List<Student>
)
