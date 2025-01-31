package com.belajar.mylearnnative.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.constants.Constants
import com.belajar.mylearnnative.model.Player

class DetailPlayerActivity : AppCompatActivity() {

    private lateinit var tvFullName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvRole: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_player)
        initComponents()

        val data = intent.getParcelableExtra<Player>(Constants.DETAIL_PLAYER)

        tvFullName.text = data?.fullName
        tvUsername.text = "(${data?.team?.name} ${data?.ign})"
        tvRole.text = data?.playerRole?.name

    }

    private fun initComponents() {
        tvFullName = findViewById(R.id.tvDetailFullName)
        tvUsername = findViewById(R.id.tvDetailUsername)
        tvRole = findViewById(R.id.tvDetailRole)
    }


}