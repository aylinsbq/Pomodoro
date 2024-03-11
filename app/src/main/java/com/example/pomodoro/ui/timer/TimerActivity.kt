package com.example.pomodoro.ui.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.pomodoro.R
import com.example.pomodoro.databinding.ActivityTimerBinding
import com.example.pomodoro.ui.main.MainActivity.Companion.ID_POMODORO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimerBinding
    private lateinit var navController: NavController
    private val viewModel: TimerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        collectDataPomodoro()
        initUI()

    }

    private fun collectDataPomodoro() {
        val idPomodoro = intent.extras?.getString(ID_POMODORO) ?: ""
        if (idPomodoro.isNotEmpty()) {
            lifecycleScope.launch {
                viewModel.findObject(idPomodoro)
                    .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                    .collect {
                        Log.i("yey", it.name)
                    }
            }
        }
    }

    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {
        val navHost=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHost.navController
        binding.bottomNav.setupWithNavController(navController)
    }
}