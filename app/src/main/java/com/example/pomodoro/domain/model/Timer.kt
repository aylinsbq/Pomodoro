package com.example.pomodoro.domain.model

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Timer(): EmbeddedRealmObject {
    var hour:Int=0
    var min:Int=0
    var sec:Int=0
    var pomodoro:PomodoroTask?=null
    constructor(hour:Int, min:Int,sec:Int):this(){
        this.hour = hour
        this.min= min
        this.sec= sec

    }
}