package com.example.pomodoro.domain.model

import javax.inject.Inject

data class Timer @Inject constructor( val hour:Int=0,  val min:Int, val sec:Int=0){
}
