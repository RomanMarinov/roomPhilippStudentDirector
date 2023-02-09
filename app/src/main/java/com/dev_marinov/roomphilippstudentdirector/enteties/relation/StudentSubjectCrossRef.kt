package com.dev_marinov.roomphilippstudentdirector.enteties.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.dev_marinov.roomphilippstudentdirector.enteties.Director
import com.dev_marinov.roomphilippstudentdirector.enteties.School
import com.dev_marinov.roomphilippstudentdirector.enteties.Student


// вспомогательный класс, который связывает таблицы Student и Subject
// это отношения n to m
// и только в этом классе будет анлотация @Entity
@Entity (primaryKeys = ["studentName", "subjectName"]) // два поля это первичный ключ
data class StudentSubjectCrossRef( // перекрестная ссылка
    val studentName: String,
    val subjectName: String
)