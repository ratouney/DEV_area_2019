package com.example.area

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


object APICalls {

    private val client = OkHttpClient()
    var ip = "51.75.69.196"
    var port = "3001"

    object Get {

        fun UsersList() {
            val request = Request.Builder()
                    .url("http://$ip:$port/user")
                    .get()
                    .build();

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    Log.e("USER LIST", response.code().toString())
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
                    .url("http://$ip:$port/service")
                    .get()
                    .build();

            val call = client.newCall(request)
            call.enqueue(callback)
            return call
        }

        fun Reactions(service : String?="") : String {
            var s = "?serviceId=$service"
            if (service == null) {
                s = ""
            }
            val request = Request.Builder()
                    .url("http://$ip:$port/area/reaction$s")
                    .get()
                    .build();

            client.newCall(request).execute().use { response ->
                Log.e("GET REACTIONS", response.code().toString())

                if (response.code() == 200) {
                    val data = JSONObject(response.body()!!.string())
                    println(data);
                    return data.getJSONArray("data").toString()
                }
            }
            return ""
        }


        fun Actions(service : String?="") : String {
            var s = "?serviceId=$service"
            if (service == null) {
                s = ""
            }
            val request = Request.Builder()
                    .url("http://$ip:$port/area/action$s")
                    .get()
                    .build();

            client.newCall(request).execute().use { response ->
                Log.e("GET ACTIONS", response.code().toString())

                if (response.code() == 200) {
                    val data = JSONObject(response.body()!!.string())
                    println(data);
                    return data.getJSONArray("data").toString()
                }

            }
            return ""
        }

        fun Area() : String {
            val tok = UserInfo.getInstance().APItok
            if (tok == null)
                return ""
            val request = Request.Builder()
                    .url("http://$ip:$port/area/me?token=$tok")
                    .get()
                    .build();

            client.newCall(request).execute().use { response ->
                Log.e("GET AREA", response.code().toString())

                if (response.code() == 200) {
                    val data = JSONObject(response.body()!!.string())
                    println(data);
                    return data.getJSONArray("data").toString()
                }

            }
            return ""
        }

        fun TokenAvailable() {
            val tok = UserInfo.getInstance().APItok
            if (tok == null)
                return
            val request = Request.Builder()
                    .url("http://$ip:$port/token?token=$tok")
                    .get()
                    .build();

            client.newCall(request).enqueue(object : okhttp3.Callback {
                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    Log.e("TOKEN AVAILABLE", response.code().toString())
                    if (response.code() == 200) {
                        val data = JSONObject(response.body()!!.string())
                        println(data);
                        val ll = data.getJSONArray("data")
                        for (i in 0 until ll.length()) {
                            val item = ll.getJSONObject(i)
                            ServicesInfoCallback.getService(item.getJSONObject("service").getString("name"))?.gotToken = true
                        }
                        ServicesInfoCallback.Show()
                    }
                }

                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    Log.e("TAG", "An error has occurred $e")
                }
            })
        }
    }


    object Post {

        fun NewUser(username : String, password : String, email : String): Boolean {
            val mediaType: MediaType? = MediaType.parse("application/x-www-form-urlencoded")
            val body = RequestBody.create(mediaType, "username=$username&password=$password&email=$email")
            val request = Request.Builder()
                    .url("http://$ip:$port/user/new")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build()
            client.newCall(request).execute().use { response ->

                Log.e("NEW USER", response.code().toString())
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
                    .url("http://$ip:$port/session/login")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build()

            client.newCall(request).execute().use { response ->

                Log.e("LOG USER", response.code().toString())
                if (response.code() == 201) {
                    val data = JSONObject(response.body()!!.string())
                    println(data);
                    UserInfo.getInstance().APItok = data.getString("data")
                } else {

                    return false
                }
            }
            return true
        }

        fun NewToken(token : String?, serviceId : String?): Boolean {


            val tok = UserInfo.getInstance().APItok
            if (tok == null || token == null || serviceId == null)
                return false
            val mediaType = MediaType.parse("application/x-www-form-urlencoded")
            val body = RequestBody.create(mediaType, "serviceId=$serviceId&token=$token")
            val request = Request.Builder()
                    .url("http://$ip:$port/token/new?token=$tok")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build()

            client.newCall(request).execute().use { response ->

                Log.e("NEW TOKEN", response.code().toString())
                if (response.code() == 201) {
                    println("Token POSTED")
                    val data = JSONObject(response.body()!!.string())
                    println(data);
                    APICalls.GET.TokenAvailable()
                } else {
                    val data = JSONObject(response.body()!!.string())
                    println(data);
                    return false
                }
            }
            return true
        }

        fun LinkArea(action : String?, reaction : String?, name :String?) : Boolean {

            val tok = UserInfo.getInstance().APItok
            val us = UserInfo.getInstance().username
            if (tok == null || action == null || reaction == null)
                return false
            val n = "$action:$name:$reaction:$us"
            val mediaType = MediaType.parse("application/x-www-form-urlencoded")
            val body = RequestBody.create(mediaType, "actionId=$action&reactionId=$reaction&name=$n&timeCheck=5")
            val request = Request.Builder()
                    .url("http://$ip:$port/area/new?token=$tok")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build()

            client.newCall(request).execute().use { response ->

                Log.e("LINK AREA", response.code().toString())
                if (response.code() == 201) {
                    println("AREA LINKED")
                    val data = JSONObject(response.body()!!.string())
                    println(data);
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
