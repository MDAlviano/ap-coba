package com.belajar.mylearnnative.ui.adapter

import android.app.Activity
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.model.Player
import java.net.HttpURLConnection
import java.net.URL

class PlayerAdapter(private var playerList: List<Player>, private val onClick: (Player) -> Unit) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerImg: ImageView = view.findViewById(R.id.iPlayer)
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
        val img = " http://10.0.2.2:5000/players/${item.image}"

        Thread {
            try {
                val url = URL(img)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)

                (holder.itemView.context as Activity).runOnUiThread {
                    holder.playerImg.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()

        holder.playerName.text = item.ign
        holder.playerRole.text = "(${item.playerRole.name})"
        holder.itemView.setOnClickListener { onClick(item) }
    }
}