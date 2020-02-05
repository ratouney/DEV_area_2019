package com.example.area

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException


object ServiceConnection {

    var client_id = "";
    var secret = ""
    var service = ""

    fun ImgurAuth() : String {
        client_id = "e59ba362671594e"
        secret = "";
        service = "Imgur"
        return ("https://api.imgur.com/oauth2/authorize?client_id=$client_id&response_type=token")
    }

    fun SpotifyAuth() : String {
        client_id = "c299f837f4ff4872ab27a1a00a6c7bdf"
        secret = "4097d12b01a449e39005967679d2efd0"
        service = "Spotify"
        return ("https://accounts.spotify.com/authorize?client_id=$client_id&response_type=code&redirect_uri=com.example.area://area")
    }

    fun DiscordAuth() : String {
        client_id = "664402435900702741"
        secret = "CO6-NbleKHQnCRk2pPAcg_hS8jHTuiuj"
        service = "Discord"
        return ("https://discordapp.com/api/oauth2/authorize?client_id=$client_id&redirect_uri=com.example.area%3A%2F%2Farea&response_type=code&scope=email")
    }

    fun GoogleAuth(context: Context) : Intent {
        client_id = "602804385318-hj4udn9f6rg3u3gb5ds45tiv3amdc5vr.apps.googleusercontent.com"
        secret = "SvRITnPEuR9Jcj60gDrTaGS3"
        service = "Google"

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(client_id)
            .requestServerAuthCode(client_id)
            .requestEmail()
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
            println("user :$user")
            println("code :$code")

            ServiceConnection.getTokenFromCode(
                code,
                "https://oauth2.googleapis.com/token",
                "https://leaflighted.com"
            );
            val myIntent = Intent(context, After::class.java)
            context.startActivity(myIntent)
        } catch (e: ApiException) {
            Log.e(
                "failed code=", e.statusCode.toString()
            )
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


    fun ParseCode(liste: List<String>?) {
        val code = liste?.get(liste.indexOf("code") + 1)
        println(code);
        if (service == "Spotify" && code != null) {
            getTokenFromCode(code, "https://accounts.spotify.com/api/token");
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

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                Log.e("Tag", response.code().toString())
                if (response.code() == 200) {
                    println("Success")
                    val data = JSONObject(response.body()!!.string())
                    println(data);
                    UserInfo.getInstance().token = data.getString("access_token")
                    println(UserInfo.getInstance().token)
                } else {
                    Log.e("Tag", response.code().toString())
                }
            }
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("TAG", "An error has occurred $e")
            }

        })
    }


}