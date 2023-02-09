package com.dev_marinov.roomphilippstudentdirector

import androidx.room.*
import com.dev_marinov.roomphilippstudentdirector.enteties.Director
import com.dev_marinov.roomphilippstudentdirector.enteties.School
import com.dev_marinov.roomphilippstudentdirector.enteties.Student
import com.dev_marinov.roomphilippstudentdirector.enteties.Subject
import com.dev_marinov.roomphilippstudentdirector.enteties.relation.*

// интерфейс для определния функций доступа к бд
@Dao
interface SchoolDao {
    // запросы в бд должны быть в фоне т.е. suspend

    // если я хочу вставить школу, которая уже есть в бд, то замениться новой
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director: Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    // функция вставки перекрестной ссылки на предмет студента
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(crossRef: StudentSubjectCrossRef)


    // возвращает все школы и всех директоров у которых есть определенное название школы
    // т.к. руму надо выполнить несколько запросов потому что мы объединяем две таблицы в SchoolAndDirector
    // и могут быть проблемы с многопоточностью, т.е в промежутках запроса и получения бд может измениться
    // мы анатируем потокобезопасной @Transaction (она нужна только ralation table)
    @Transaction
    // выбери из таблицы school где schoolName = schoolName
    @Query(value = "SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName: String) : List<SchoolAndDirector>

    // передать название школы и получить всех учеников, которые ходят в этоу школу
    @Transaction
    @Query(value = "SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolWithStudents(schoolName: String) : List<SchoolWithStudent>

    // функция получения всех студентов по какому либо предмету
    @Transaction
    // выбери из таблицы school где subjectName = subjectName
    @Query(value = "SELECT * FROM subject WHERE subjectName = :subjectName")
    suspend fun getStudentsOfSubject(subjectName: String) : List<SubjectWithStudents>

    // функция получения всех занятий по какому либо студенту
    @Transaction
    // выбери из таблицы school где studentName = studentName
    @Query(value = "SELECT * FROM student WHERE studentName = :studentName")
    suspend fun getSubjectsOfStudent(studentName: String) : List<StudentWithSubjects>
}