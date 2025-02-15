package com.belajar.mylearnnative.ui.activity

import android.os.Bundle
import android.widget.TextClock
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.ui.fragments.main.PlayerFragment
import com.belajar.mylearnnative.ui.fragments.main.TeamFragment
import com.belajar.mylearnnative.utils.PreferencesHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var tvUsername: TextView
    private lateinit var tvDate: TextView
    private lateinit var tcTopBar: TextClock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        getUsername()
        setupBottomNavigation()
        setupDateTime()

        if (savedInstanceState == null) {
            val mFragmentManager = supportFragmentManager
            val teamFragment = TeamFragment()
            mFragmentManager.beginTransaction()
                .add(R.id.fragment_container, teamFragment, TeamFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun setupDateTime() {
        tcTopBar.apply {
            format24Hour = "HH:mm:ss a"
            format12Hour = null
        }

        updateDate()
    }

    private fun updateDate() {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("en", "EN"))

        tvDate.text = dateFormat.format(calendar.time)
    }

    private fun initComponents() {
        tvUsername = findViewById(R.id.tvUsername)
        tvDate = findViewById(R.id.tvDate)
        tcTopBar = findViewById(R.id.tcTopBar)
    }

    private fun getUsername() {
        val preferences = PreferencesHelper(this)
        val username = preferences.getUsername() ?: "Guest"
        tvUsername.text = "Halo, $username 👋"
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
                .addToBackStack(null)
                .commit()
            true
        }
    }
}
