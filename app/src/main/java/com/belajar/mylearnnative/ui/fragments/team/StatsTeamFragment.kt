package com.belajar.mylearnnative.ui.fragments.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.constants.Constants
import com.belajar.mylearnnative.model.Team

class StatsTeamFragment : Fragment() {

    private lateinit var tvDeaths: TextView
    private lateinit var tvKills: TextView
    private lateinit var tvAssists: TextView
    private lateinit var tvGold: TextView
    private lateinit var tvDamage: TextView
    private lateinit var tvLordKills: TextView
    private lateinit var tvTortoiseKills: TextView
    private lateinit var tvTowerDestroy: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats_team, container, false)

        tvDeaths = view.findViewById(R.id.tvDeaths)
        tvKills = view.findViewById(R.id.tvKills)
        tvAssists = view.findViewById(R.id.tvAssists)
        tvGold = view.findViewById(R.id.tvGold)
        tvDamage = view.findViewById(R.id.tvDamage)
        tvLordKills = view.findViewById(R.id.tvLordKills)
        tvTortoiseKills = view.findViewById(R.id.tvTortoiseKills)
        tvTowerDestroy = view.findViewById(R.id.tvTowerDestroy)

        val data = arguments?.getParcelable<Team>(Constants.DETAIL_TEAM)

        tvDeaths.text = "${data?.deaths.toString()} Deaths"
        tvKills.text = "${data?.kills.toString()} Kills"
        tvAssists.text = "${data?.assists.toString()} Assists"
        tvGold.text = "${data?.gold.toString()} Gold"
        tvDamage.text = "${data?.damage.toString()} Damage"
        tvLordKills.text = "${data?.lordKills.toString()} Lord Kills"
        tvTortoiseKills.text = "${data?.tortoiseKills.toString()} Tortoise Kills"
        tvTowerDestroy.text = "${data?.towerDestroy.toString()} Tower Destroy"

        return view
    }


}