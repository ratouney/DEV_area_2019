package com.example.area

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_service_connections.*

class Services : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_service_connections, container, false)
    }

    override fun onStart() {
        super.onStart()
        weeb.visibility = View.GONE

        Googleb.setOnClickListener {
            hideUI()
            startActivityForResult(ServiceConnection.GoogleAuth(context!!, false), 9001)
        }

        Spotifyb.setOnClickListener {
            hideUI()
            ServiceConnection.loadAuthPage(ServiceConnection.SpotifyAuth(false), weeb, context!!)
        }

        Imgurb.setOnClickListener {
            hideUI()
            ServiceConnection.loadAuthPage(ServiceConnection.ImgurAuth(false), weeb, context!!)
        }
    }

    fun hideUI() {
        Googleb.visibility = View.GONE
        Spotifyb.visibility = View.GONE
        Imgurb.visibility = View.GONE
        weeb.visibility = View.VISIBLE
    }
}
