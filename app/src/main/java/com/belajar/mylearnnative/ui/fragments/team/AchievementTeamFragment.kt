package com.belajar.mylearnnative.ui.fragments.team

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.constants.Constants
import com.belajar.mylearnnative.model.Achievement
import com.belajar.mylearnnative.model.Team
import com.belajar.mylearnnative.repository.DataRepository
import com.belajar.mylearnnative.ui.adapter.AchievementAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AchievementTeamFragment : Fragment() {

    private lateinit var rvAchievement: RecyclerView

    private var listAchievement = mutableListOf<Achievement>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_achievement_team, container, false)
        rvAchievement = view.findViewById(R.id.rvAchievementTeam)

        val data = arguments?.getParcelable<Team>(Constants.DETAIL_TEAM)!!

        setupRecyclerView()
        loadAchievement(data.id)

        return view
    }

    private fun loadAchievement(data: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val achievements = DataRepository.getListAchievement("api/achievements", data)
                withContext(Dispatchers.Main) {
                    if (achievements.isNotEmpty()) {
                        listAchievement.clear()
                        listAchievement.addAll(achievements)
                        rvAchievement.adapter?.notifyDataSetChanged()
                    } else {
                        Toast.makeText(requireContext(), "No Teams Found", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("Team Fragment", "Error: ${e.message}")
                    Toast.makeText(requireContext(), "Error loading teams", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val achievementAdapter = AchievementAdapter(listAchievement)
        rvAchievement.apply {
            adapter = achievementAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }


}