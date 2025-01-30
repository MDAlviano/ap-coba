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
import kotlin.concurrent.thread

class RegisterActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var bRegister: Button
    private lateinit var tvToLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        initComponents()
        onClickHandlers()

    }

    private fun initComponents() {
        etFullName = findViewById(R.id.etFullNameReg)
        etUsername = findViewById(R.id.etUsernameReg)
        etEmail = findViewById(R.id.etEmailReg)
        etPhoneNumber = findViewById(R.id.etPhoneNumberReg)
        etPassword = findViewById(R.id.etPasswordReg)
        bRegister = findViewById(R.id.bRegister)
        tvToLogin = findViewById(R.id.tvToLogin)
    }

    private fun onClickHandlers() {
        bRegister.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val phoneNumber = etPhoneNumber.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (fullName.isEmpty() && username.isEmpty() && email.isEmpty() && phoneNumber.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                performRegister(fullName, username, email, phoneNumber, password)
            }
        }

        tvToLogin.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun performRegister(
        fullName: String,
        username: String,
        email: String,
        phoneNumber: String,
        password: String
    ) {
        thread {
            val response = AuthRepository.register(fullName, username, email, phoneNumber, password)

            runOnUiThread {
                if (response != null) {
                    try {
                        Toast.makeText(this, "Succeed", Toast.LENGTH_SHORT).show()
                        navigateToLogin()
                    } catch (e: Exception) {
                        Log.e("RegisterAct", e.message.toString())
                    }
                } else {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                    Log.e("RegisterAct", response ?: "skill issue")
                }
            }

        }
    }

}
