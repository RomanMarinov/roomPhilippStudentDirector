package com.dev_marinov.roomphilippstudentdirector.enteties.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.dev_marinov.roomphilippstudentdirector.enteties.Director
import com.dev_marinov.roomphilippstudentdirector.enteties.School


// вспомогательный класс, который связывает таблицы Director и School
// это простые отношения one to one
data class SchoolAndDirector(
    // рум поймет и включить все области которые есть в школе после написания @Embedded
    @Embedded val school: School,
    @Relation(
        // родительский столбец, который сылается на родительский столбец
        parentColumn = "schoolName",
        // столбец, который не является превичным ключом, но сравнивается со столбцом "schoolName", который является епрвичным ключом
        entityColumn = "schoolName"
    )
    // не комментируя его @Embedded мы говорим руму что director это объект наших отношений
    val director: Director
)