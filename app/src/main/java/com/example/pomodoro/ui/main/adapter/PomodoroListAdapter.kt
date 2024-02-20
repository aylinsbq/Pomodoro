package com.example.pomodoro.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pomodoro.R
import com.example.pomodoro.domain.model.NewPomodoro

class PomodoroListAdapter(private var pomodoroList:List<NewPomodoro>):
    RecyclerView.Adapter<PomodoroListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PomodoroListViewHolder {
        return PomodoroListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pomodoro,parent,false))
    }

    override fun getItemCount(): Int = pomodoroList.size

    override fun onBindViewHolder(holder: PomodoroListViewHolder, position: Int) {
        holder.render(pomodoroList[position])
    }
    fun updateList(list:List<NewPomodoro>){
        pomodoroList = list
        notifyDataSetChanged()
    }
}