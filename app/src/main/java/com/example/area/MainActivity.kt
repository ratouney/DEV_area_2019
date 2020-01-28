package com.example.area

import android.annotation.TargetApi
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var client_id = "";
    var secret = ""
    var service = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button6.setOnClickListener{
            button6.visibility = View.GONE
            GoogleAuth()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 9001) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )
            val token = account?.idToken ?: ""
            val user = account?.givenName ?: ""

            UserInfo.getInstance().token = token;
            UserInfo.getInstance().username = user;
            println(token)
            println(user)

            val myIntent = Intent(this, After::class.java)
            this.startActivity(myIntent)
        } catch (e: ApiException) {
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }
    }

    fun ImgurAuth() {
        client_id = "e59ba362671594e"
        secret = "";
        service = "Imgur"
        loadAuthPage("https://api.imgur.com/oauth2/authorize?client_id=$client_id&response_type=token")
    }

    fun SpotifyAuth() {
        client_id = "c299f837f4ff4872ab27a1a00a6c7bdf"
        secret = "4097d12b01a449e39005967679d2efd0"
        service = "Spotify"
        loadAuthPage("https://accounts.spotify.com/authorize?client_id=$client_id&response_type=code&redirect_uri=com.example.area://area")
    }

    fun DiscordAuth() {
        client_id = "664402435900702741"
        secret = "CO6-NbleKHQnCRk2pPAcg_hS8jHTuiuj"
        service = "Discord"
        loadAuthPage("https://discordapp.com/api/oauth2/authorize?client_id=$client_id&redirect_uri=com.example.area%3A%2F%2Farea&response_type=code&scope=email")
    }

    fun GoogleAuth() {
        client_id = "602804385318-hj4udn9f6rg3u3gb5ds45tiv3amdc5vr.apps.googleusercontent.com"
        secret = "SvRITnPEuR9Jcj60gDrTaGS3"
        service = "Google"

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(client_id)
            .requestEmail()
            .build()
        val GoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = GoogleSignInClient.signInIntent
        startActivityForResult(
            signInIntent, 9001
        )

        //loadAuthPage("https://accounts.google.com/o/oauth2/v2/auth/authorize?client_id=$client_id&redirect_uri=com.example.area%3A%2F%2Farea&response_type=token")
    }


    fun loadAuthPage(url : String) {
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

    fun ParseToken(liste: List<String>?) {
        val token = liste?.get(liste.indexOf("access_token") + 1)
        val user = liste?.get(liste.indexOf("account_username") + 1)
        UserInfo.getInstance().token = token;
        UserInfo.getInstance().username = user;
        println(token)
        println(user)
    }

    fun ParseCode(liste: List<String>?) {
        val code = liste?.get(liste.indexOf("code") + 1)
        println(code);
        if (service == "Spotify" && code != null) {
            getTokenFromCode(code, "https://accounts.spotify.com/api/token");
        } else if (service == "Discord" && code != null) {
            getTokenFromCode(code, "https://discordapp.com/api/v6/oauth2/token");
        }
    }

    fun getTokenFromCode(code: String, url: String) {
        val client = OkHttpClient()

        val body = FormBody.Builder()
            .add("client_id", client_id)
            .add("client_secret", secret)
            .add("grant_type", "authorization_code")
            .add("code", code)
            .add("redirect_uri", "com.example.area://area")
            .build()


        val request = Request.Builder()
            .url(url)
            .header("Content-Type", "application/json")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.code() == 200) {
                    println("Success")
                    val data = JSONObject(response.body()!!.string())
                    println(data);
                    UserInfo.getInstance().token = data.getString("access_token")
                    println( data.getString("access_token"))
                }
            }
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("TAG", "An error has occurred $e")
            }

        })
    }
}
