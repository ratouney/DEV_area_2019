package com.example.area

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


object APICalls {

    private val client = OkHttpClient()
    var ip = "http://10.14.58.26"
    var port = "3001"

    class Get {

        fun UsersList() {
            val request = Request.Builder()
                    .url("$ip:$port/user")
                    .get()
                    .build();

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    Log.e("Tag", response.code().toString())
                    if (response.code() == 200) {
                        val data = JSONObject(response.body()!!.string())
                        println(data);
                    }
                }

                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.e("TAG", "An error has occurred $e")
                }

            })
        }
    }


    class Post {

        fun NewUser(username : String, password : String, email : String = "", callback: Callback): Call {
            val mediaType: MediaType? = MediaType.parse("application/x-www-form-urlencoded")
            val body = RequestBody.create(mediaType, "username=$username&password=$password&email=$email")
            val request = Request.Builder()
                    .url("$ip:$port/user/new")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build()

            val call = client.newCall(request)
            call.enqueue(callback)
            return call
        }

        fun LogUser(username: String, password: String, callback: Callback): Call {
            val mediaType: MediaType? = MediaType.parse("application/x-www-form-urlencoded")
            val body = RequestBody.create(mediaType, "username=$username&password=$password")
            val request = Request.Builder()
                    .url("$ip:$port/session/login")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build()

            val call = client.newCall(request)
            call.enqueue(callback)
            return call
        }
    }

    val GET = Get()
    val POST = Post()

}