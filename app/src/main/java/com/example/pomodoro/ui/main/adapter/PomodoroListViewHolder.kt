package com.example.pomodoro.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pomodoro.R
import com.example.pomodoro.databinding.ItemPomodoroBinding
import com.example.pomodoro.domain.model.PomodoroTask
import org.mongodb.kbson.ObjectId

class PomodoroListViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemPomodoroBinding.bind(view)
    fun render(pomodoroTask: PomodoroTask, position: Int, onitemSelected: (ObjectId) -> Unit) {
        if (position==0){
            binding.btnItemPomodoro.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.small_tomato,0)
            binding.btnItemPomodoro.setPadding(170,binding.btnItemPomodoro.paddingTop,170,binding.btnItemPomodoro.paddingBottom)
        }
        binding.btnItemPomodoro.text=pomodoroTask.name
        binding.btnItemPomodoro.setOnClickListener{
            onitemSelected(pomodoroTask.id)
        }
    }
}