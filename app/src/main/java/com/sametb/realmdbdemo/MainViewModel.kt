package com.sametb.realmdbdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sametb.realmdbdemo.model.placeholder.*
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/*
* RealmDB Demo.com.sametb.realmdbdemo
* Created by SAMET BAYAT 
* on 8.02.2024 at 10:13 AM
* Copyright (c) 2024 UNITED WORLD. All rights reserved.
*/

class MainViewModel : ViewModel() {
    private val realm = MyApp.realm

    val courses =
        MyApp.realm
            .query<Course>(
                // tip: you can use the following queries
//                query = "enrolledStudents.name == $0", args = arrayOf("Xavier")
//                query = "enrolledStudents.@count > 1"
//                query = "teacher.address.fullName CONTAINS $0", args = arrayOf("Samet")
            )
            .asFlow()
            .map { results -> results.list.toList() }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                emptyList()
            )

    var courseDetails: Course? by mutableStateOf(null)
        private set

    init {
        // After initial run of the app, we will have the following entries in the database
        // Teacher 1-to-1 Address
        // Teacher 1-to-many Course
        // Students many-to-many Course
        // For following runs we do not need to create the entries again

        if (!isDbExists())
            // TODO: So it can be commented out
            createEntries()
    }

    fun showCourseDetails(course: Course) {
        courseDetails = course
    }

    fun hideCourseDetails() {
        courseDetails = null
    }

    private fun createEntries() {

        // if we have already created the entries, we do not need to create them again
        viewModelScope.launch {
            realm.write {
                course1.teacher = teacher1
                course2.teacher = teacher1
                course3.teacher = teacher2

                address1.teacher = teacher1
                address2.teacher = teacher2

                course1.enrolledStudents.add(student1)
                course2.enrolledStudents.add(student2)
                course3.enrolledStudents.addAll(listOf(student2, student3))

                // no need for address
                copyToRealm(teacher1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(teacher2, updatePolicy = UpdatePolicy.ALL)

                copyToRealm(student1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(student2, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(student3, updatePolicy = UpdatePolicy.ALL)

                copyToRealm(course1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(course2, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(course3, updatePolicy = UpdatePolicy.ALL)

            }
        }
    }

    fun isDbExists(): Boolean {
        return realm.query<Course>().first() != null

    }

    fun deleteCourse() {
        viewModelScope.launch {
            realm.write {
                val course = courseDetails ?: return@write
                val latestCourse = findLatest(course) ?: return@write
                delete(latestCourse)

                courseDetails = null
            }
        }
    }
}