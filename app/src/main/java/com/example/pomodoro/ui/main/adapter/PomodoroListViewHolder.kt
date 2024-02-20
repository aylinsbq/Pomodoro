package com.example.pomodoro.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pomodoro.databinding.ItemPomodoroBinding
import com.example.pomodoro.domain.model.NewPomodoro

class PomodoroListViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemPomodoroBinding.bind(view)
    fun render(newPomodoro: NewPomodoro) {
        binding.btnItemPomodoro.text=newPomodoro.name
    }
}