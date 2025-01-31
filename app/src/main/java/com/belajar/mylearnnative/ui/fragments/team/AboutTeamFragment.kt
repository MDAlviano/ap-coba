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

class AboutTeamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_team, container, false)

        val data = arguments?.getParcelable<Team>(Constants.DETAIL_TEAM)
        view.findViewById<TextView>(R.id.tvDetailNameTeam).text = data?.name
        view.findViewById<TextView>(R.id.tvDetailAboutTeam).text = data?.about

        return view
    }
}