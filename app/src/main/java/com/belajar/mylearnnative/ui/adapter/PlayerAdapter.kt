package com.belajar.mylearnnative.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.model.Player

class PlayerAdapter(private var playerList: List<Player>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val playerName: TextView = view.findViewById(R.id.tvPlayerName)
        val playerRole: TextView = view.findViewById(R.id.tvRole)
    }

    fun updateData(newPlayers: List<Player>) {
        playerList = newPlayers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewholder_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = playerList[position]
        holder.playerName.text = item.ign
        holder.playerRole.text = "(${item.playerRole.name})"
    }
}