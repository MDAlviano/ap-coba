package com.belajar.mylearnnative.ui.fragments.team

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.belajar.mylearnnative.R
import com.belajar.mylearnnative.constants.Constants
import com.belajar.mylearnnative.model.Team
import java.net.HttpURLConnection
import java.net.URL

class AboutTeamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_team, container, false)

        val data = arguments?.getParcelable<Team>(Constants.DETAIL_TEAM)
        getImage(data?.logo256, view.findViewById(R.id.iDetailTeam))
        view.findViewById<TextView>(R.id.tvDetailNameTeam).text = data?.name
        view.findViewById<TextView>(R.id.tvDetailAboutTeam).text = data?.about

        return view
    }

    private fun getImage(data: String?, view: View) {
        Thread {
            try {
                val img = " http://10.0.2.2:5000/logos/${data}"
                val url = URL(img)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)

                requireActivity().runOnUiThread {
                    view.findViewById<ImageView>(R.id.iDetailTeam).setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}