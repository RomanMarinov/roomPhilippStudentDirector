package com.dev_marinov.roomphilippstudentdirector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.dev_marinov.roomphilippstudentdirector.enteties.Director
import com.dev_marinov.roomphilippstudentdirector.enteties.School
import com.dev_marinov.roomphilippstudentdirector.enteties.Student
import com.dev_marinov.roomphilippstudentdirector.enteties.Subject
import com.dev_marinov.roomphilippstudentdirector.enteties.relation.StudentSubjectCrossRef
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val directors = listOf(
        Director("Mike Litoris", "Jake Wharton School"),
        Director("Jack Goff", "Kotlin School"),
        Director("Chris P. Chicken", "JetBrains School")
    )
    private val schools = listOf(
        School("Jake Wharton School"),
        School("Kotlin School"),
        School("JetBrains School")
    )
    private val subjects = listOf(
        Subject("Dating for programmers"),
        Subject("Avoiding depression"),
        Subject("Bug Fix Meditation"),
        Subject("Logcat for Newbies"),
        Subject("How to use Google")
    )
    private val students = listOf(
        Student("Beff Jezos", 2, "Kotlin School"),
        Student("Mark Suckerberg", 5, "Jake Wharton School"),
        Student("Gill Bates", 8, "Kotlin School"),
        Student("Donny Jepp", 1, "Kotlin School"),
        Student("Hom Tanks", 2, "JetBrains School")
    )
    private val studentSubjectRelations = listOf(
        StudentSubjectCrossRef("Beff Jezos", "Dating for programmers"),
        StudentSubjectCrossRef("Beff Jezos", "Avoiding depression"),
        StudentSubjectCrossRef("Beff Jezos", "Bug Fix Meditation"),
        StudentSubjectCrossRef("Beff Jezos", "Logcat for Newbies"),
        StudentSubjectCrossRef("Mark Suckerberg", "Dating for programmers"),
        StudentSubjectCrossRef("Gill Bates", "How to use Google"),
        StudentSubjectCrossRef("Donny Jepp", "Logcat for Newbies"),
        StudentSubjectCrossRef("Hom Tanks", "Avoiding depression"),
        StudentSubjectCrossRef("Hom Tanks", "Dating for programmers")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dao = SchoolDatabase.getInstance(this).schoolDao

        lifecycleScope.launch {
            directors.forEach { dao.insertDirector(it) }
            schools.forEach { dao.insertSchool(it) }
            subjects.forEach { dao.insertSubject(it) }
            students.forEach { dao.insertStudent(it) }
            studentSubjectRelations.forEach { dao.insertStudentSubjectCrossRef(it) }

            // получать надо в рамках этого жц, иначе запись может быть позже чем получение
            val schoolWithDirector = dao.getSchoolAndDirectorWithSchoolName("Kotlin School")
            val schoolWithStudents = dao.getSchoolWithStudents("Kotlin School")
        }

    }
}