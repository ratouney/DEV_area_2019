package com.example.area

import android.R.attr
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.area.ServiceConnection.ParseCode
import com.example.area.ServiceConnection.ParseToken
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
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
            loadAuthPage(ServiceConnection.SpotifyAuth())
        }

        ButtonLogIn.setOnClickListener{

        }

        RegisterPage.setOnClickListener{
            val myIntent = Intent(this, Register::class.java)
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
        val webView = weeb;

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
                    val intent = Intent(this@MainActivity, After::class.java)
                    ContextCompat.startActivity(this@MainActivity, intent, null)
                    return false
                }
                return false
            }
        }
        webView.setWebViewClient(client)
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
