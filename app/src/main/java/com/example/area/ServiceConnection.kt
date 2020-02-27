package com.example.area

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import com.google.android.gms.tasks.Task
import homeactivity.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


object ServiceConnection {

    var client_id = "";
    var secret = ""
    var service = ""
    var fc : Boolean = false
    var con : Boolean = true

    fun LocalAuth(u : EditText, p : EditText) {
        val userInfo = UserInfo.getInstance()
        userInfo.username = u.text.toString()
    }


    fun ImgurAuth(c : Boolean=true) : String {
        //client_id = "e59ba362671594e"
        client_id = "094ee1cffcac340"
        secret = "";
        service = "Imgur"
        con = c

        return ("https://api.imgur.com/oauth2/authorize?client_id=$client_id&response_type=token")
    }

    fun SpotifyAuth(c : Boolean=true) : String {
        //client_id = "c299f837f4ff4872ab27a1a00a6c7bdf"
        //secret = "4097d12b01a449e39005967679d2efd0"
        client_id = "d3ebae5610894ca48c9f66794214252b"
        secret = "766fb8262eda43e18ae80c3e7d713f9e"
        service = "Spotify"
        con = c

        return ("https://accounts.spotify.com/authorize?client_id=$client_id&response_type=code&redirect_uri=com.example.area://area&scope=user-read-email user-modify-playback-state")
    }

    fun DiscordAuth(c : Boolean=true) : String {
        client_id = "664402435900702741"
        secret = "CO6-NbleKHQnCRk2pPAcg_hS8jHTuiuj"
        service = "Discord"
        con = c

        return ("https://discordapp.com/api/oauth2/authorize?client_id=$client_id&redirect_uri=com.example.area%3A%2F%2Farea&response_type=code&scope=email")
    }

    fun GoogleAuth(context: Context, c : Boolean=true) : Intent {
        
        client_id = "602804385318-hj4udn9f6rg3u3gb5ds45tiv3amdc5vr.apps.googleusercontent.com"
        secret = "SvRITnPEuR9Jcj60gDrTaGS3"
        //client_id = "87061903784-oplbf08ai2kgc6gb8v4lolhual8go4o0.apps.googleusercontent.com"
        //secret = "BjaOWkyU6mVl-2eOkSvZYCr6"
        service = "Gmail"
        con = c
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(client_id)
            .requestServerAuthCode(client_id)
            .requestEmail()
            .requestScopes(Scope("email"), Scope("https://www.googleapis.com/auth/drive"), Scope("https://www.googleapis.com/auth/drive.file"), Scope("https://www.googleapis.com/auth/spreadsheets"), Scope("https://www.googleapis.com/auth/drive.readonly"), Scope("https://www.googleapis.com/auth/drive.metadata.readonly"), Scope("https://www.googleapis.com/auth/drive.appdata"), Scope("https://www.googleapis.com/auth/drive.metadata"), Scope("https://www.googleapis.com/auth/drive.photos.readonly"), Scope("https://mail.google.com/"), Scope("https://www.googleapis.com/auth/gmail.modify"), Scope("https://www.googleapis.com/auth/gmail.compose"), Scope("https://www.googleapis.com/auth/gmail.send"), Scope("https://www.googleapis.com/auth/gmail.readonly"), Scope("https://www.googleapis.com/auth/gmail.metadata"))
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        val signInIntent = googleSignInClient.signInIntent
        return signInIntent;
    }


    fun handleGoogleResult(completedTask: GoogleSignInResult, context: Context) {
        try {
            val account = completedTask.signInAccount
            val code = account?.serverAuthCode ?: ""
            val user = account?.givenName ?: ""

            UserInfo.getInstance().username = user;
            UserInfo.getInstance().mail = account?.email;
            UserInfo.getInstance().id = account?.id;

            println("user :$user")
            println("code :$code")

            ServiceConnection.getTokenFromCode(code,"https://oauth2.googleapis.com/token","https://leaflighted.com");

            connect(context)

        } catch (e: ApiException) {
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }
    }

    fun getUserInfoSpotify() {
        val client = OkHttpClient()
        val token = UserInfo.getInstance().token

        val request = Request.Builder()
                .url("https://api.spotify.com/v1/me")
                .header("Authorization", "Bearer $token")
                .get()
                .build()

        client.newCall(request).execute().use { response ->

            if (response.code() == 200) {
                Log.e("Tag", response.code().toString())
                val data = JSONObject(response.body()!!.string())
                println(data)

                UserInfo.getInstance().username = data.getString("display_name")
                UserInfo.getInstance().id = data.getString("id")
                UserInfo.getInstance().mail = data.getString("email")

            }
        }
    }

    fun ParseToken(liste: List<String>?) {
        val token = liste?.get(liste.indexOf("access_token") + 1)
        val user = liste?.get(liste.indexOf("account_username") + 1)
        UserInfo.getInstance().token = token;
        UserInfo.getInstance().username = user;
        println(token)
        println(user)
    }


    fun connect(context: Context) : Boolean {

        val intent = Intent(context, HomeActivity::class.java)
        if (fc) {
            if (!APICalls.POST.NewUser(UserInfo.getInstance().username, UserInfo.getInstance().id, UserInfo.getInstance().mail)) {
                val ii = Intent(context, ConnectionActivity::class.java)
                ContextCompat.startActivity(context, ii, null)
                Toast.makeText(context,"Connection failed, please try again", Toast.LENGTH_LONG).show()
                return false
            }
        }
        if (UserInfo.getInstance().username != null && UserInfo.getInstance().id != null) {
            if (!con && UserInfo.getInstance().APItok !=  null) {
                ContextCompat.startActivity(context, intent, null)
                if (service == "Gmail")
                    APICalls.POST.NewToken(UserInfo.getInstance().token, ServicesInfoCallback.getService("Sheet")!!.id!!)
                APICalls.POST.NewToken(UserInfo.getInstance().token, ServicesInfoCallback.getService(service)!!.id!!)
                return false
            }
            if (APICalls.POST.LogUser(UserInfo.getInstance().username, UserInfo.getInstance().id)) {
                ContextCompat.startActivity(context, intent, null)
                if (service == "Gmail")
                    APICalls.POST.NewToken(UserInfo.getInstance().token, ServicesInfoCallback.getService("Sheet")!!.id!!)
                APICalls.POST.NewToken(UserInfo.getInstance().token, ServicesInfoCallback.getService(service)!!.id!!)
                return false
            }
        }
        val ii = Intent(context, ConnectionActivity::class.java)
        ContextCompat.startActivity(context, ii, null)
        Toast.makeText(context,"Connection failed, please try again", Toast.LENGTH_LONG).show()
        return false
    }

    fun ParseCode(liste: List<String>?) {
        val code = liste?.get(liste.indexOf("code") + 1)
        println(code);
        if (service == "Spotify" && code != null) {
            getTokenFromCode(code, "https://accounts.spotify.com/api/token");
            getUserInfoSpotify()
        } else if (service == "Discord" && code != null) {
            getTokenFromCode(code, "https://discordapp.com/api/v6/oauth2/token");
        }
    }

    fun getTokenFromCode(code: String, url: String, redirect_uri : String="com.example.area://area") {
        println("get token from code : " + code  + " to " + url + " with " + secret)
        val client = OkHttpClient()

        val body = FormBody.Builder()
            .add("client_id", client_id)
            .add("client_secret", secret)
            .add("grant_type", "authorization_code")
            .add("code", code)
            .add("redirect_uri", redirect_uri)
            .build()


        val request = Request.Builder()
            .url(url)
            .header("Content-Type", "application/json")
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->

            Log.e("REQUEST TOKEN $service", response.code().toString())
            if (response.code() == 200) {
                println("Success")
                val data = JSONObject(response.body()!!.string())
                println(data);
                UserInfo.getInstance().token = data.getString("access_token")
            } else {
                Log.e("Tag", response.code().toString())
            }
        }
    }

    fun loadAuthPage(url : String, webView : WebView, context: Context) {

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
                        return false
                    }

                    return connect(context)
                }
                return false
            }
        }
        webView.setWebViewClient(client)
    }

}
