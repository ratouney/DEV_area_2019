package com.example.area

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_service_connections.*

class Services : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_connections)
        weeb.visibility = View.GONE

        Googleb.setOnClickListener {
            hideUI()
            startActivityForResult(ServiceConnection.GoogleAuth(this, false), 9001)
        }

        Spotifyb.setOnClickListener {
            hideUI()
            ServiceConnection.loadAuthPage(ServiceConnection.SpotifyAuth(false), weeb, this)
        }

        Imgurb.setOnClickListener {
            hideUI()
            ServiceConnection.loadAuthPage(ServiceConnection.ImgurAuth(false), weeb, this)
        }
    }

    fun hideUI() {
        Googleb.visibility = View.GONE
        Spotifyb.visibility = View.GONE
        Imgurb.visibility = View.GONE
        weeb.visibility = View.VISIBLE
    }
}
