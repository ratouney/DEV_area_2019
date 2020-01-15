package com.example.area

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse



class After : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after)

        val resp = AuthorizationResponse.fromIntent(intent)
        val ex = AuthorizationException.fromIntent(intent)
        if (resp != null) {
            Log.i("Auth :", resp.accessToken!!);
        } else {
            Log.e("Auth :", ex.toString());
            // authorization failed, check ex for more details
        }
    }
}
