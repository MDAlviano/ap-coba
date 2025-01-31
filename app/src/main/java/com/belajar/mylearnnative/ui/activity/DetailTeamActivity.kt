package com.belajar.mylearnnative.ui.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.constants.Constants
import com.belajar.mylearnnative.model.Team
import com.belajar.mylearnnative.ui.fragments.team.AboutTeamFragment
import com.belajar.mylearnnative.ui.fragments.team.AchievementTeamFragment
import com.belajar.mylearnnative.ui.fragments.team.PlayerTeamFragment
import com.belajar.mylearnnative.ui.fragments.team.StatsTeamFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailTeamActivity : AppCompatActivity() {

    private lateinit var tvBack: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        val data = intent.getParcelableExtra<Team>(Constants.DETAIL_TEAM)
        initComponents()

        val fragment = AboutTeamFragment()
        val bundle = Bundle()
        bundle.putParcelable(Constants.DETAIL_TEAM, data)
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_team, fragment)
            .commit()

        setupBottomNav()
        onClickHandler()

    }

    private fun onClickHandler() {
        tvBack.setOnClickListener {
            finish()
        }
    }

    private fun setupBottomNav() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_nav_team)
        val data = intent.getParcelableExtra<Team>(Constants.DETAIL_TEAM)

        bottomNavigation.setOnItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.nav_about -> createFragment(AboutTeamFragment(), data)
                R.id.nav_achievement -> createFragment(AchievementTeamFragment(), data)
                R.id.nav_stats -> createFragment(StatsTeamFragment(), data)
                R.id.nav_player -> createFragment(PlayerTeamFragment(), data)
                else -> AboutTeamFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_team, fragment)
                .addToBackStack(null)  // Tambahkan ini agar bisa kembali ke fragment sebelumnya
                .commit()
            true
        }
    }

    private fun initComponents() {
        tvBack = findViewById(R.id.tvBackDetailTeam)
    }

    private fun createFragment(fragment: Fragment, data: Team?): Fragment {
        val bundle = Bundle()
        bundle.putParcelable(Constants.DETAIL_TEAM, data)
        fragment.arguments = bundle
        return fragment
    }
}