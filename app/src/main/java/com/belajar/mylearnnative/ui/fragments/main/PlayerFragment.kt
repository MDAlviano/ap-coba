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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.constants.Constants
import com.belajar.mylearnnative.model.Player
import com.belajar.mylearnnative.repository.DataRepository
import com.belajar.mylearnnative.ui.activity.DetailPlayerActivity
import com.belajar.mylearnnative.ui.adapter.PlayerAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlayerFragment : Fragment() {

    private lateinit var svPlayer: SearchView
    private lateinit var rvPlayer: RecyclerView

    private var listPlayer = mutableListOf<Player>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        svPlayer = view.findViewById(R.id.svPlayer)
        rvPlayer = view.findViewById(R.id.rvPlayer)

        setupRecyclerView()
        setupSearchView()
        loadPlayers()

        return view
    }

    private fun setupSearchView() {
        svPlayer.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList =
                    listPlayer.filter { it.ign.contains(newText ?: "", ignoreCase = true) }
                (rvPlayer.adapter as? PlayerAdapter)?.updateData(filteredList)
                return true
            }
        })
    }

    private fun setupRecyclerView() {
        val playerAdapter = PlayerAdapter(listPlayer) { player ->
            val intent = Intent(requireContext(), DetailPlayerActivity::class.java)
            intent.putExtra(Constants.DETAIL_PLAYER, player)
            startActivity(intent)
        }
        rvPlayer.apply {
            adapter = playerAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun loadPlayers() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val players = DataRepository.getListPlayers("api/players")
                withContext(Dispatchers.Main) {
                    if (players.isNotEmpty()) {
                        listPlayer.clear()
                        listPlayer.addAll(players)
                        rvPlayer.adapter?.notifyDataSetChanged()
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