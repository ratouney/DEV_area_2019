package com.example.area

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

object APICalls {

    private val client = OkHttpClient()

    class Get {

        fun UsersList() {
            val request = Request.Builder()
                    .url("http://10.14.58.26:3001/user")
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

        fun NewUser() {

        }
    }

    val GET = Get()
    val POST = Post()

}