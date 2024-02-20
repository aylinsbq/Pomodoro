package com.example.pomodoro.ui.main

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pomodoro.databinding.ActivityMainBinding
import com.example.pomodoro.databinding.DialogNewPomodoroBinding
import com.example.pomodoro.domain.model.NewPomodoro
import com.example.pomodoro.domain.model.Timer
import com.example.pomodoro.ui.main.adapter.PomodoroListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val listOfPomodoro = mutableListOf(
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Study", Timer(0, 30), Timer(0, 7), Timer(0, 20)),
        /*NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),
        NewPomodoro("Classic", Timer(0, 25), Timer(0, 5), Timer(0, 15)),*/
    )
    private lateinit var pomodoroListAdapter: PomodoroListAdapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initList()
        initListeners()
    }

    private fun initList() {
        pomodoroListAdapter = PomodoroListAdapter(listOfPomodoro)
        binding.rvListPomodoro.layoutManager = LinearLayoutManager(this)
        binding.rvListPomodoro.adapter=pomodoroListAdapter
    }

    private fun initListeners() {
        binding.fabNewPomodoro.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialogBind = DialogNewPomodoroBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(dialogBind.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //Pomodoro Default Time
        setHours(dialogBind.npPomodoroHours)
        setHours(dialogBind.npShortBreakHours)
        setHours(dialogBind.npLongBreakHours)

        setMinutesOrSeconds(dialogBind.npPomodoroMinutes, 25)
        setMinutesOrSeconds(dialogBind.npShortBreakMinutes, 5)
        setMinutesOrSeconds(dialogBind.npLongBreakMinutes, 15)

        setMinutesOrSeconds(dialogBind.npPomodoroSeconds, 0)
        setMinutesOrSeconds(dialogBind.npShortBreakSeconds, 0)
        setMinutesOrSeconds(dialogBind.npLongBreakSeconds, 0)

        //Saving New Pomodoro Data
        dialogBind.btnCreatePomodoro.setOnClickListener {
            val nameNewPomodoro = dialogBind.etAddName.text.toString()

            val hourPomodoro = dialogBind.npPomodoroHours.value
            val minPomodoro = dialogBind.npPomodoroMinutes.value
            val secPomodoro = dialogBind.npPomodoroSeconds.value
            val pomodoro = Timer(hourPomodoro, minPomodoro, secPomodoro)

            val hourShortBreak = dialogBind.npShortBreakHours.value
            val minShortBreak = dialogBind.npShortBreakMinutes.value
            val secShortBreak = dialogBind.npShortBreakSeconds.value
            val shortBreak = Timer(hourShortBreak, minShortBreak, secShortBreak)

            val hourLongBreak = dialogBind.npLongBreakHours.value
            val minLongBreak = dialogBind.npLongBreakMinutes.value
            val secLongBreak = dialogBind.npLongBreakSeconds.value
            val longBreak = Timer(hourLongBreak, minLongBreak, secLongBreak)

            val newPomodoro = NewPomodoro(nameNewPomodoro, pomodoro, shortBreak, longBreak)
            listOfPomodoro.add(newPomodoro)
            Log.i("Beach","$listOfPomodoro")
            pomodoroListAdapter.updateList(listOfPomodoro)
            dialog.hide()
        }
        dialog.show()

    }


    private fun setHours(npHours: NumberPicker) {
        npHours.maxValue = 23
        npHours.minValue = 0
        npHours.value = 0
    }

    private fun setMinutesOrSeconds(npMinutes: NumberPicker, value: Int) {
        npMinutes.maxValue = 59
        npMinutes.minValue = 0
        npMinutes.value = value
    }


}