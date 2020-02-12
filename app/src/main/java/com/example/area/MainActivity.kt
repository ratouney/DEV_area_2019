package com.example.area

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weeb.visibility = View.GONE

        GoogleLogIn.setOnClickListener{
            hideAllUI()
            startActivityForResult(ServiceConnection.GoogleAuth(this), 9001)
        }

        SpotifyLogIn.setOnClickListener{
            hideAllUI()
            ServiceConnection.loadAuthPage(ServiceConnection.SpotifyAuth(), weeb, this@MainActivity)
        }

        ButtonLogIn.setOnClickListener{

        }

        RegisterPage.setOnClickListener{
            val myIntent = Intent(this, ConnectionActivity::class.java)
            startActivity(myIntent)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 9001) {
            ServiceConnection.handleGoogleResult(Auth.GoogleSignInApi.getSignInResultFromIntent(data), this)
        }
    }

    fun hideAllUI() {
        ButtonLogIn.visibility = View.GONE
        GoogleLogIn.visibility = View.GONE
        SpotifyLogIn.visibility = View.GONE
        UserNameLogIn.visibility = View.GONE
        RegisterPage.visibility = View.GONE
        PasswordLogIn.visibility = View.GONE
        textView3.visibility = View.GONE
        weeb.visibility = View.VISIBLE
    }


}
