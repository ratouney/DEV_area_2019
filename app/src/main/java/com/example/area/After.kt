package com.example.area

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_after.*
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.TokenResponse
import net.openid.appauth.AuthorizationService
import android.provider.SyncStateContract.Helpers.update

class After : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after)

        textView.text = UserInfo.getInstance().username;
        textView2.text = UserInfo.getInstance().token;
    }
}
