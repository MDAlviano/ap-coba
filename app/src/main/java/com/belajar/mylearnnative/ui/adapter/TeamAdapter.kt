package com.belajar.mylearnnative.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.model.Team

class TeamAdapter(private var teamList: List<Team>, private val onCLick: (Team) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.iTeam)
        val teamName: TextView = view.findViewById(R.id.tvTeamName)
    }

    fun updateData(newTeams: List<Team>) {
        teamList = newTeams
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_team, parent, false)
        return TeamViewHolder(view)
    }

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val item = teamList[position]
        holder.teamName.text = item.name
        holder.itemView.setOnClickListener { onCLick(item) }
    }

}