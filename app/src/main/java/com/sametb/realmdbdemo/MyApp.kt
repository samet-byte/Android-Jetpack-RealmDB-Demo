package com.sametb.realmdbdemo

import android.app.Application
import com.sametb.realmdbdemo.model.placeholder.Address
import com.sametb.realmdbdemo.model.placeholder.Course
import com.sametb.realmdbdemo.model.placeholder.Student
import com.sametb.realmdbdemo.model.placeholder.Teacher
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration


/*
* RealmDB Demo.com.sametb.realmdbdemo
* Created by SAMET BAYAT 
* on 8.02.2024 at 10:08 AM
* Copyright (c) 2024 UNITED WORLD. All rights reserved.
*/

class MyApp : Application() {

    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Address::class,
                    Teacher::class,
                    Course::class,
                    Student::class
                )
            )
        )
    }
}