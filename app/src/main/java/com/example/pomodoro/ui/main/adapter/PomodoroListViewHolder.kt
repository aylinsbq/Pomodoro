package com.example.pomodoro.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pomodoro.R
import com.example.pomodoro.databinding.ItemPomodoroBinding
import com.example.pomodoro.domain.model.NewPomodoro

class PomodoroListViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemPomodoroBinding.bind(view)
    fun render(newPomodoro: NewPomodoro, position: Int) {
        binding.btnItemPomodoro.text=newPomodoro.name
        if (position==0){
            binding.btnItemPomodoro.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.small_tomato,0)
            binding.btnItemPomodoro.setPadding(170,binding.btnItemPomodoro.paddingTop,170,binding.btnItemPomodoro.paddingBottom)
        }
    }
}