package com.example.area

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.area.ServiceConnection.ParseCode
import com.example.area.ServiceConnection.ParseToken
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        weebos.visibility = View.GONE

        GoogleRegister.setOnClickListener{
            hideAllUI()
            startActivityForResult(ServiceConnection.GoogleAuth(this), 9001)
        }

        SpotifyRegister.setOnClickListener{
            loadAuthPage(ServiceConnection.SpotifyAuth())
        }

        ButtonRegister.setOnClickListener{

        }

        RegisterPage.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 9001) {
            ServiceConnection.handleGoogleResult(Auth.GoogleSignInApi.getSignInResultFromIntent(data), this)
        }
    }


    fun loadAuthPage(url : String) {
        hideAllUI()
        val webView = weebos;

        webView.getSettings().setJavaScriptEnabled(true)
        webView.loadUrl(url)
        val client = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                println(Uri.parse(url))
                if (Uri.parse(url).scheme == "com.example.area") {
                    val liste = url?.split("#", "=", "&", "?")
                    println(liste)
                    if (liste?.contains("access_token")== true) {
                        println("Acces Token :")
                        ParseToken(liste)
                    } else if (liste?.contains("code")== true) {
                        println("Code :")
                        ParseCode(liste)
                    } else {
                        println("yo WTF")
                        println("yo WTF")
                        println("yo WTF")
                    }
                    val intent = Intent(this@Register, After::class.java)
                    ContextCompat.startActivity(this@Register, intent, null)
                    return false
                }
                return false
            }
        }
        webView.setWebViewClient(client)
    }

    fun hideAllUI() {
        ButtonRegister.visibility = View.GONE
        GoogleRegister.visibility = View.GONE
        SpotifyRegister.visibility = View.GONE
        UserNameRegister.visibility = View.GONE
        PasswordRegister.visibility = View.GONE
        weebos.visibility = View.VISIBLE
    }


}
