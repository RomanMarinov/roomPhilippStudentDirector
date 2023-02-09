package com.dev_marinov.roomphilippstudentdirector

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev_marinov.roomphilippstudentdirector.enteties.Director
import com.dev_marinov.roomphilippstudentdirector.enteties.School
import com.dev_marinov.roomphilippstudentdirector.enteties.Student
import com.dev_marinov.roomphilippstudentdirector.enteties.Subject
import com.dev_marinov.roomphilippstudentdirector.enteties.relation.StudentSubjectCrossRef

@Database(
    entities = [ // все таблицы
        School::class,
        Student::class,
        Director::class,
        Subject::class,
        StudentSubjectCrossRef::class
    ],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract val schoolDao: SchoolDao
    // база данных должна быть создана способом сохранения потока
    // то обернем ее в статик
    companion object {
        @Volatile // потокобезопасное изменение переменной INSTANCE
        // при ее изменении, значение ее сразу видно другим потокам
        private var INSTANCE: SchoolDatabase? = null
        fun getInstance(context: Context) : SchoolDatabase {
            // все что делает synchronized, то что он убедиться что его тело выполняется в одном потоке
            // и чтобы не один другой поток не мог получить доступ к бд, в то время когда мы фактически
            // находимся внутри тела synchronized, т.е. два потока одновременно не смогут создать бд
            // потому что тогда у нас было бы два экземпляра бд
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder( // создание бд
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}