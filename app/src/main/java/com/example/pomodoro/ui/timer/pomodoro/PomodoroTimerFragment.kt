package com.example.pomodoro.ui.timer.pomodoro

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pomodoro.R
import com.example.pomodoro.databinding.FragmentPomodoroTimerBinding
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PomodoroTimerFragment : Fragment() {
    private var _binding: FragmentPomodoroTimerBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentPomodoroTimerBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.circularProgressBar.apply {
            // Set Progress
            progress = 0f
            setProgressWithAnimation(50f, 1000)

        }
    }

}