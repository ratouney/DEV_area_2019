package com.example.area

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


object APICalls {

    private val client = OkHttpClient()
    var ip = "http://51.75.69.196"
    var port = "3001"

    object Get {

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

        fun Services(callback: Callback): Call {
            val request = Request.Builder()
                    .url("$ip:$port/service")
                    .get()
                    .build();

            val call = client.newCall(request)
            call.enqueue(callback)
            return call
        }
    }


    object Post {

        fun NewUser(username : String, password : String, email : String): Boolean {
            val mediaType: MediaType? = MediaType.parse("application/x-www-form-urlencoded")
            val body = RequestBody.create(mediaType, "username=$username&password=$password&email=$email")
            val request = Request.Builder()
                    .url("$ip:$port/user/new")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build()
            client.newCall(request).execute().use { response ->

                Log.e("Tag", response.code().toString())
                if (response.code() == 201) {
                    val data = JSONObject(response.body()!!.string())
                    println(data);
                    val d = data.getJSONObject("data")
                    UserInfo.getInstance().id = d.getString("id")
                    println(UserInfo.getInstance().id)
                }  else {
                    return false
                }
            }
            return true
        }

        fun LogUser(username: String, password: String): Boolean {
            val mediaType: MediaType? = MediaType.parse("application/x-www-form-urlencoded")
            val body = RequestBody.create(mediaType, "username=$username&password=$password")
            val request = Request.Builder()
                    .url("$ip:$port/session/login")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build()

            client.newCall(request).execute().use { response ->

                Log.e("Tag", response.code().toString())
                if (response.code() == 201) {
                    val data = JSONObject(response.body()!!.string())
                    println(data);
                } else {

                    return false
                }
            }
            return true
        }

        fun NewToken(token : String, serviceId : String): Boolean {

            val mediaType = MediaType.parse("application/x-www-form-urlencoded")
            val body = RequestBody.create(mediaType, "serviceId=$serviceId&token=$token")
            val request = Request.Builder()
                    .url("$ip:$port/token/new?token=$token")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build()

            client.newCall(request).execute().use { response ->

                Log.e("Tag", response.code().toString())
                if (response.code() == 200) {
                    println("yup")
                } else {
                    return false
                }
            }
            return true
        }
    }

    val GET = Get
    val POST = Post

}

object ServicesInfoCallback : Callback {

    var services : List<Service>? = null

    override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
        Log.e("Tag", response.code().toString())
        if (response.code() == 200) {
            val data = JSONObject(response.body()!!.string())
            println(data);
            val gson = GsonBuilder().create()
            services = gson.fromJson(data.getJSONArray("data").toString(),Array<Service>::class.java).toList()
        }
    }
    override fun onFailure(call: Call, e: IOException) {
        Log.e("TAG", "An error has occurred $e")
    }

    fun getService(name : String) : Service? {
        services?.forEach {
            if (it.name.equals(name)) {
                return it
            }
        }
        return null
    }
}