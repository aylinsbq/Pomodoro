package com.example.pomodoro.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodoro.domain.model.PomodoroTask
import com.example.pomodoro.domain.model.Timer
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(realmDataBase: Realm) : ViewModel() {
    private val realm = realmDataBase

    val pomodoroTasks = realm.query<PomodoroTask>()
        .asFlow()
        .map { results -> results.list.toList() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    init {
        if (realm.query<PomodoroTask>().find().isEmpty()) {
            createInitEntries()
        }
    }

    private fun createInitEntries() {
        viewModelScope.launch {
            realm.write {
                val pomodoroTimer = Timer(0, 25, 0)
                val shortBreakTimer = Timer(0, 5, 0)
                val longBreakTimer = Timer(0, 15, 0)
                val pomodoroClassic =
                    PomodoroTask("Classic", pomodoroTimer, shortBreakTimer, longBreakTimer)
                Log.i("Ucha", pomodoroClassic.name)
                pomodoroTimer.pomodoro = pomodoroClassic
                shortBreakTimer.pomodoro = pomodoroClassic
                longBreakTimer.pomodoro = pomodoroClassic

                copyToRealm(pomodoroClassic, updatePolicy = UpdatePolicy.ALL)

            }
        }
    }
    fun createPomodoroTask(pomodoroTask: PomodoroTask) {
        viewModelScope.launch {
            realm.write {
                copyToRealm(pomodoroTask, updatePolicy = UpdatePolicy.ALL)

            }
        }
    }


}