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
                } else {
                    return false
                }
            }
            return true
        }

        fun LinkArea(action : String?, reaction : String?, name :String?="yup") : Boolean {

            val tok = UserInfo.getInstance().APItok
            if (tok == null || action == null || reaction == null)
                return false

            val mediaType = MediaType.parse("application/x-www-form-urlencoded")
            val body = RequestBody.create(mediaType, "actionId=$action&reactionId=$reaction&name=$name&timeCheck=5")
            val request = Request.Builder()
                    .url("http://$ip:$port/area/new?token=$tok")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .post(body)
                    .build()

            client.newCall(request).execute().use { response ->

                Log.e("LINK AREA", response.code().toString())
                if (response.code() == 201) {
                    println("AREA LINKED")
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