package com.example.pomodoro.ui.main

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import com.example.pomodoro.databinding.ActivityMainBinding
import com.example.pomodoro.databinding.DialogNewPomodoroBinding
import com.example.pomodoro.domain.model.NewPomodoro
import com.example.pomodoro.domain.model.Timer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val listOfPomodoros = mutableListOf<NewPomodoro>(
        NewPomodoro("Classic", Timer(0,25),Timer(0,5),Timer(0,15 ))
    )

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.fabNewPomodoro.setOnClickListener { showDiolog() }
    }

    private fun showDiolog() {
        val dialogBind = DialogNewPomodoroBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(dialogBind.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //Pomodoro Default Time
        setHours(dialogBind.npPomodoroHours, 0)
        setHours(dialogBind.npShortBreakHours, 0)
        setHours(dialogBind.npLongBreakHours, 0)

        setMinutes(dialogBind.npPomodoroMinutes, 25)
        setMinutes(dialogBind.npShortBreakMinutes, 5)
        setMinutes(dialogBind.npLongBreakMinutes, 15)

        setMinutes(dialogBind.npPomodoroSeconds, 0)
        setMinutes(dialogBind.npShortBreakSeconds, 0)
        setMinutes(dialogBind.npLongBreakSeconds, 0)

        //Saving New Pomodoro Data
        dialogBind.btnCreatePomodoro.setOnClickListener {
            val nameNewPomodoro = dialogBind.etAddName.text.toString()

            val hourPomodoro = dialogBind.npPomodoroHours.value
            val minPomodoro = dialogBind.npPomodoroMinutes.value
            val secPomodoro = dialogBind.npPomodoroSeconds.value
            val pomodoro=Timer(hourPomodoro,minPomodoro,secPomodoro)

            val hourShortBreak = dialogBind.npShortBreakHours.value
            val minShortBreak = dialogBind.npShortBreakMinutes.value
            val secShortBreak = dialogBind.npShortBreakSeconds.value
            val shortBreak=Timer(hourShortBreak,minShortBreak,secShortBreak)

            val hourLongBreak = dialogBind.npLongBreakHours.value
            val minLongBreak = dialogBind.npLongBreakMinutes.value
            val secLongBreak = dialogBind.npLongBreakSeconds.value
            val longBreak=Timer(hourLongBreak,minLongBreak,secLongBreak)

            val newPomodoro = NewPomodoro(nameNewPomodoro, pomodoro,shortBreak,longBreak)

            listOfPomodoros.add(newPomodoro)
            Log.i("Beach","$listOfPomodoros")
            dialog.hide()
        }
        dialog.show()

    }

    private fun setHours(npHours: NumberPicker, hour: Int) {
        npHours.maxValue = 23
        npHours.minValue = 0
        npHours.value = hour
    }

    private fun setMinutes(npMinutes: NumberPicker, minutes: Int) {
        npMinutes.maxValue = 59
        npMinutes.minValue = 0
        npMinutes.value = minutes
    }

    private fun setSeconds(npSeconds: NumberPicker, seconds: Int) {
        npSeconds.maxValue = 59
        npSeconds.minValue = 0
        npSeconds.value = seconds
    }
}