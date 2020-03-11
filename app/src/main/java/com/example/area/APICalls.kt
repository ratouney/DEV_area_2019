package com.example.area

import AppFragment.MyArea
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
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
                    val data = JSONObject(response.body()!!.string())
                    println(data);
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

        fun ParseAction(name : String, param : String, json : JSONObject)  : JSONObject{
            //send && draft
            if (name.equals("gmail.sendMessage") || name.equals("gmail.createDraft")) {
                val t = param.split(", ")
                print(param + " -> " + t)
                try {
                    json.put("senderMail", t[0])
                    json.put("destMail", t[1])
                    json.put("title", t[2])
                    json.put("text", t[3])
                } catch (e : Exception) {
                }
            }
            //create sheet
            if (name.equals("sheet.createSheet")) {
                val t = param.split(", ")
                try {
                    json.put("title", t[0])
                    json.put("name", t[1])
                } catch (e : Exception) {
                }
            }
            //sheet change
            if (name.equals("sheet.sheetChange")) {
                if (param.contains("http")) {
                    json.put("id", param.split("/")[-2])
                } else {
                    json.put("id", param)
                }

            }
            //volume
            if (name.equals("spotify.setVolume")) {
                json.put("data", param)
            }
            //meteo
            if (name.equals("meteo.weatherByCity")) {
                json.put("name", param)
            }
            //uv
            if (name.equals("meteo.uvLimitReached")) {
                val t = param.split(", ")
                try {
                    json.put("name", t[0])
                    json.put("data", t[1])
                } catch (e : Exception) {
                }
            }
            //upload
            if (name.equals("imgur.uploadPic")) {
                val t = param.split(", ")
                try {
                    json.put("url", t[0])
                    json.put("title", t[1])
                    json.put("text", t[2])
                } catch (e : Exception) {
                }
            }
            //tag && fav
            if (name.equals("imgur.isThereNewPicForTag") || name.equals("imgur.userGotNewFav") || name.equals("pokemon.getPokemonByName")) {
                json.put("name", param)
            }
            //new vote && new comment
            if (name.equals("imgur.getNewVote") || name.equals("imgur.getNewComment")) {
                if (param.contains("http")) {
                    json.put("id", param.split("/").last())
                } else {
                    json.put("id", param)
                }
            }
            //bio imgur
            if (name.equals("imgur.changeUserBio")) {
                val t = param.split(", ")
                try {
                    json.put("name", t[0])
                    json.put("text", t[1])
                } catch (e : Exception) {
                }
            }
            return json
        }

        fun LinkArea(action : String?, reaction : String?, param1:String?="", param2:String?="") : Boolean {

            val tok = UserInfo.getInstance().APItok
            val us = UserInfo.getInstance().username
            if (tok == null || action == null || reaction == null)
                return false

            val p1 : String = param1 ?: ""
            val p2 : String = param2 ?: ""
            val n = "$action:${kotlin.random.Random.nextInt()}:$reaction:$us"
            var data = JSONObject()
            data = ParseAction(MyArea.actionName!!, p1, data)
            data = ParseAction(MyArea.reactionName!!, p2, data)

            println()
            println(data.toString())
            println()

            val mediaType = MediaType.parse("application/x-www-form-urlencoded")
            val body = RequestBody.create(mediaType, "actionId=$action&reactionId=$reaction&name=$n&timeCheck=5&data=${data}")
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

    object Delete {

        fun Area() {

        }

    }

    val GET = Get
    val POST = Post
    val DELETE = Delete

}
