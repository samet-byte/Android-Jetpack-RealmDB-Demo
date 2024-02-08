package com.sametb.realmdbdemo.model.placeholder

import io.realm.kotlin.ext.realmListOf


/*
* RealmDB Demo.com.sametb.realmdbdemo.model.placeholder
* Created by SAMET BAYAT 
* on 8.02.2024 at 10:16 AM
* Copyright (c) 2024 UNITED WORLD. All rights reserved.
*/

val address1 = Address().apply {
    fullName = "Samet Bayat"
    street = "Kadikoy"
    houseNumber = 123
    zip = 12345
    city = "Istanbul"
}

val address2 = Address().apply {
    fullName = "John Doe"
    street = "Merkez"
    houseNumber = 1
    zip = 36000
    city = "Kars"
}

val course1 = Course().apply {
    name = "Math"
}

val course2 = Course().apply {
    name = "Science"
}

val course3 = Course().apply {
    name = "History"
}


val teacher1 = Teacher().apply {
    address = address1
    courses = realmListOf(course1, course2)
}

val teacher2 = Teacher().apply {
    address = address2
    courses = realmListOf(course3)
}

val student1 = Student().apply {
    name = "Xavier"
}

val student2 = Student().apply {
    name = "Yvonne"
}

val student3 = Student().apply {
    name = "Zelda"
}