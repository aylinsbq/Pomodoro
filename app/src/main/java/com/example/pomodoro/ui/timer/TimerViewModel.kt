package com.example.pomodoro.ui.timer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodoro.domain.model.PomodoroTask
import com.example.pomodoro.ui.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.notifications.ResultsChange
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(realmDataBase: Realm) : ViewModel() {
    private val realm = realmDataBase

    fun findObject(idPomodoro: String):StateFlow<PomodoroTask> {
        return realm.query<PomodoroTask>("idString == $0", idPomodoro)
            .asFlow()
            .map { result -> result.list.get(0) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                PomodoroTask()
            )
    }
}