package com.belajar.mylearnnative.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.ui.fragments.PlayerFragment
import com.belajar.mylearnnative.ui.fragments.TeamFragment
import com.belajar.mylearnnative.utils.PreferencesHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var tvUsername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        getUsername()
        setupBottomNavigation()

        if (savedInstanceState == null) {
            val mFragmentManager = supportFragmentManager
            val teamFragment = TeamFragment()
            mFragmentManager.beginTransaction()
                .add(R.id.fragment_container, teamFragment, TeamFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun initComponents() {
        tvUsername = findViewById(R.id.tvUsername)
    }

    private fun getUsername() {
        val preferences = PreferencesHelper(this)
        val username = preferences.getUsername() ?: "Guest"
        tvUsername.text = "Hello $username!"
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigation.setOnItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.nav_team -> TeamFragment()
                R.id.nav_player -> PlayerFragment()
                else -> TeamFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)  // Tambahkan ini agar bisa kembali ke fragment sebelumnya
                .commit()
            true
        }
    }
}
