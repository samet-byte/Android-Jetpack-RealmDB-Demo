package com.sametb.realmdbdemo.model.placeholder

import io.realm.kotlin.types.EmbeddedRealmObject


/*
* RealmDB Demo.com.sametb.realmdbdemo.model.placeholder
* Created by SAMET BAYAT 
* on 8.02.2024 at 2:00 AM
* Copyright (c) 2024 UNITED WORLD. All rights reserved.
*/

// Teacher 1-to-1 Address
// Teacher 1-to-many Course
// Students many-to-many Course

class Address: EmbeddedRealmObject {
    var fullName: String = ""
    var street: String = ""
    var houseNumber: Int = 0
    var zip: Int = 0
    var city: String = ""
    var teacher: Teacher? = null
}