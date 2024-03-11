package com.example.pomodoro.ui.timer.longBreak

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pomodoro.R
import com.example.pomodoro.databinding.FragmentLongBreakTimerBinding
import com.example.pomodoro.databinding.FragmentPomodoroTimerBinding

class LongBreakTimerFragment : Fragment() {
    private var _binding: FragmentLongBreakTimerBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentLongBreakTimerBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}