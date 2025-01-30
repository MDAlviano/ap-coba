package com.belajar.mylearnnative.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.repository.AuthRepository
import com.belajar.mylearnnative.utils.PreferencesHelper
import org.json.JSONObject
import kotlin.concurrent.thread

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var bLogin: Button
    private lateinit var tvToRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        initComponents()
        onClickHandlers()


    }

    private fun initComponents() {
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        bLogin = findViewById(R.id.bLogin)
        tvToRegister = findViewById(R.id.tvToRegister)
    }

    private fun onClickHandlers() {

        bLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                performLogin(username, password)
            }
        }

        tvToRegister.setOnClickListener {
            navigateToRegister()
        }

    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }

    private fun performLogin(username: String, password: String) {
        thread {
            val response = AuthRepository.login(username, password)

            runOnUiThread {
                if (response != null) {
                    try {
                        val jsonObject = JSONObject(response)
                        val username = jsonObject.getString("username")

                        val preferences = PreferencesHelper(this)
                        preferences.saveUsername(username)

                        Toast.makeText(this, "Succeed", Toast.LENGTH_SHORT).show()
                        navigateToMain()
                    } catch (e: Exception) {
                        Log.e("LoginAct", e.message.toString())
                    }
                } else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    Log.e("LoginAct", "$response")
                }
            }
        }
    }
}