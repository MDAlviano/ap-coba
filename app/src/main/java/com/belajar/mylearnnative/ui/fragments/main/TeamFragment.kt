package com.belajar.mylearnnative.ui.fragments.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.constants.Constants
import com.belajar.mylearnnative.model.Team
import com.belajar.mylearnnative.repository.DataRepository
import com.belajar.mylearnnative.ui.activity.DetailTeamActivity
import com.belajar.mylearnnative.ui.adapter.TeamAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamFragment : Fragment() {

    private lateinit var svTeam: SearchView
    private lateinit var rvTeam: RecyclerView

    private var listTeam = mutableListOf<Team>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_team, container, false)
        rvTeam = view.findViewById(R.id.rvTeam)
        svTeam = view.findViewById(R.id.svTeam)

        setupRecyclerView()
        setupSearchView()
        loadTeams()

        Log.d("listTeam", listTeam.toString())

        return view
    }

    private fun setupRecyclerView() {
        val teamAdapter = TeamAdapter(listTeam) { team ->
            val intent = Intent(requireContext(), DetailTeamActivity::class.java)
            intent.putExtra(Constants.DETAIL_TEAM, team)
            startActivity(intent)
        }
        rvTeam.apply {
            adapter = teamAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    private fun loadTeams() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val teams = DataRepository.getListTeams("api/teams")
                withContext(Dispatchers.Main) {
                    if (teams.isNotEmpty()) {
                        listTeam.clear()
                        listTeam.addAll(teams)
                        Log.d("listTeam", teams.toString())
                        rvTeam.adapter?.notifyDataSetChanged()
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

    private fun setupSearchView() {
        svTeam.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList =
                    listTeam.filter { it.name.contains(newText ?: "", ignoreCase = true) }
                (rvTeam.adapter as? TeamAdapter)?.updateData(filteredList)
                return true
            }
        })
    }

}