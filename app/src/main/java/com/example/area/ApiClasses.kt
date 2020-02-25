package com.example.area

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import org.json.JSONObject
import java.io.IOException


class Service {
    val id : String? = null
    val name : String? = null
    var gotToken : Boolean = false
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
            services?.forEach {
                if (it.name.equals("Pokemon") || it.name.equals("Nasa") || it.name.equals("Meteo")) {
                    it.gotToken = true
                }
            }
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

    fun getServiceById(id : String) : Service? {
        services?.forEach {
            if (it.id.equals(id)) {
                return it
            }
        }
        return null
    }

    fun Show() {
        services?.forEach {
            println("${it.name} : ${it.id} : ${it.gotToken}")
        }
    }
}