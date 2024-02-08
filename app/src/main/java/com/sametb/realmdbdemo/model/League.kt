package com.sametb.realmdbdemo.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class League: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var team: Team? = null


}
