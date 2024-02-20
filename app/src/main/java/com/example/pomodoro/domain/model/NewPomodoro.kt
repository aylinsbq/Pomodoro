package com.example.pomodoro.domain.model

import javax.inject.Inject

data class NewPomodoro @Inject constructor(val name: String, private val pomodoro: Timer, private val shortBreak: Timer, private val longBreak: Timer) {
}