package com.belajar.mylearnnative.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.belajar.mylearnnative.R

class LandingScreenActivity : AppCompatActivity() {

    private lateinit var bLogin: Button
    private lateinit var bRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_screen)

        bLogin = findViewById(R.id.bToLogin)
        bRegister = findViewById(R.id.bToRegister)

        bLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        bRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}