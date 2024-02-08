package com.sametb.realmdbdemo.model

import io.realm.kotlin.ext.realmSetOf
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmSet
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Team: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()

    var name: String = ""
    var stats: Stats? = null
//    var courses: List<Course> = emptyList()
    var league: RealmSet<League> = realmSetOf()

}
