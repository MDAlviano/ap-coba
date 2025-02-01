package com.belajar.mylearnnative.ui.fragments.team

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.constants.Constants
import com.belajar.mylearnnative.model.Player
import com.belajar.mylearnnative.model.Team
import com.belajar.mylearnnative.repository.DataRepository
import com.belajar.mylearnnative.ui.activity.DetailPlayerActivity
import com.belajar.mylearnnative.ui.adapter.PlayerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayerTeamFragment : Fragment() {

    private lateinit var rvPlayerTeam: RecyclerView

    private var listPlayer = mutableListOf<Player>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player_team, container, false)
        rvPlayerTeam = view.findViewById(R.id.rvPlayerTeam)

        val data = arguments?.getParcelable<Team>(Constants.DETAIL_TEAM)!!

        setupRecyclerView()
        loadPlayers(data.id)

        return view
    }

    private fun setupRecyclerView() {
        val playerAdapter = PlayerAdapter(listPlayer) { player ->
            val intent = Intent(requireContext(), DetailPlayerActivity::class.java)
            intent.putExtra(Constants.DETAIL_PLAYER, player)
            startActivity(intent)
        }
        rvPlayerTeam.apply {
            adapter = playerAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    private fun loadPlayers(teamId: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val players = DataRepository.getListPlayerTeam("api/players/team", teamId)
                withContext(Dispatchers.Main) {
                    if (players.isNotEmpty()) {
                        listPlayer.clear()
                        listPlayer.addAll(players)
                        rvPlayerTeam.adapter?.notifyDataSetChanged()
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

}