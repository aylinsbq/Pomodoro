package com.example.pomodoro.ui.main

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pomodoro.databinding.ActivityMainBinding
import com.example.pomodoro.databinding.DialogNewPomodoroBinding
import com.example.pomodoro.domain.model.NewPomodoro
import com.example.pomodoro.domain.model.PomodoroTask
import com.example.pomodoro.domain.model.Timer
import com.example.pomodoro.ui.main.adapter.PomodoroListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel:MainViewModel by viewModels()

    private  var pomodoroListAdapter: PomodoroListAdapter= PomodoroListAdapter()

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
        lifecycleScope.launch {
            viewModel.pomodoroTasks.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect{
                    pomodoroListAdapter.updateList(it)
                }
        }
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
            val newPomodoro = PomodoroTask(nameNewPomodoro, pomodoro, shortBreak, longBreak)
            viewModel.createPomodoroTask(newPomodoro)
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