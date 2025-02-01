package com.belajar.mylearnnative.ui.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.constants.Constants
import com.belajar.mylearnnative.model.Player
import java.net.HttpURLConnection
import java.net.URL

class DetailPlayerActivity : AppCompatActivity() {

    private lateinit var iBack: ImageView
    private lateinit var iPlayer: ImageView
    private lateinit var tvFullName: TextView
    private lateinit var tvUsername: TextView
    private lateinit var tvRole: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        initComponents()

        val data = intent.getParcelableExtra<Player>(Constants.DETAIL_PLAYER)

        tvFullName.text = data?.fullName
        tvUsername.text = "(${data?.team?.name} ${data?.ign})"
        tvRole.text = data?.playerRole?.name

        getImage(data?.image)

        iBack.setOnClickListener {
            finish()
        }

    }

    private fun getImage(data: String?) {
        Thread {
            try {
                val img = " http://10.0.2.2:5000/players/${data}"
                val url = URL(img)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)

                runOnUiThread {
                    iPlayer.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun initComponents() {
        iBack = findViewById(R.id.iBackDetailPlayer)
        iPlayer = findViewById(R.id.iDetailPlayer)
        tvFullName = findViewById(R.id.tvDetailFullName)
        tvUsername = findViewById(R.id.tvDetailUsername)
        tvRole = findViewById(R.id.tvDetailRole)
    }


}