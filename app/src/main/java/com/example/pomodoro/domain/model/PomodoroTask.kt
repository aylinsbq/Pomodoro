package com.example.pomodoro.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class PomodoroTask():RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var name: String = ""
    var pomodoro:Timer?=null
    var shortBreak:Timer?=null
    var longBreak:Timer?=null
    constructor(name: String,pomodoro:Timer,shortBreak:Timer,longBreak:Timer ) : this() {
        this.name = name
        this.pomodoro= pomodoro
        this.shortBreak= shortBreak
        this.longBreak= longBreak
    }


}