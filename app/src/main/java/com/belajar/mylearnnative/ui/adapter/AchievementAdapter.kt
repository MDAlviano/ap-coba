package com.belajar.mylearnnative.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.model.Achievement

class AchievementAdapter(private var achievementList: List<Achievement>) :
    RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder>() {

    class AchievementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val achievement: TextView = view.findViewById(R.id.tvAchievementTeam)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_achievement_team, parent, false)
        return AchievementViewHolder(view)
    }

    override fun getItemCount(): Int = achievementList.size

    override fun onBindViewHolder(holder: AchievementViewHolder, position: Int) {
        val item = achievementList[position]
        holder.achievement.text = item.name
    }
}