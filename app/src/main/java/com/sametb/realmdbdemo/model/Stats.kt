package com.sametb.realmdbdemo.model

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


/*
* RealmDB Demo.com.sametb.realmdbdemo.model
* Created by SAMET BAYAT 
* on 8.02.2024 at 1:39 AM
* Copyright (c) 2024 UNITED WORLD. All rights reserved.
*/

//class Stats: RealmObject {
//    @PrimaryKey
//    var _id: ObjectId = ObjectId()

class Stats: EmbeddedRealmObject {
    var wins: Int = 0
    var losses: Int = 0
    var team: Team? = null
}