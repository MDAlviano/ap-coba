package com.belajar.mylearnnative.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.repository.AuthRepository
import com.google.android.material.textfield.TextInputEditText
import kotlin.concurrent.thread

class RegisterActivity : AppCompatActivity() {

    private lateinit var etFullName: TextInputEditText
    private lateinit var etUsername: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPhoneNumber: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var bRegister: Button
    private lateinit var tvToLogin: TextView
    private lateinit var iBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        initComponents()
        onClickHandlers()

    }

    private fun initComponents() {
        iBack = findViewById(R.id.iBackFromReg)
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

        iBack.setOnClickListener {
            finish()
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
