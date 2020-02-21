package com.example.area

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_choose_ip.*

class ChooseIp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_ip)

        ok.setOnClickListener {

            if (!ipt.text.isEmpty())
                APICalls.ip = ipt.text.toString()

            if (!portt.text.isEmpty())
                APICalls.port = portt.text.toString()

            val intent = Intent(this, ConnectionActivity::class.java)
            ContextCompat.startActivity(this, intent, null)
        }
    }
}
