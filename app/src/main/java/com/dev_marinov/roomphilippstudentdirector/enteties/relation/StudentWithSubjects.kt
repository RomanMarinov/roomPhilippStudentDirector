package com.dev_marinov.roomphilippstudentdirector.enteties.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.dev_marinov.roomphilippstudentdirector.enteties.Student
import com.dev_marinov.roomphilippstudentdirector.enteties.Subject

// это вспомогательный класс имеет доступ к студенту, который посещает список занятий
data class StudentWithSubjects(
    @Embedded
    val student: Student,
    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        // сообщаем руму какая таблица определяет это отношение
        associateBy = Junction(StudentSubjectCrossRef::class)
    )
    val subjects: List<Subject>
)
