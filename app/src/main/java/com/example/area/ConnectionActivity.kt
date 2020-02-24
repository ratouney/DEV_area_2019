package com.example.area

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.auth.api.Auth
import homeactivity.HomeActivity
import kotlinx.android.synthetic.main.activity_register.*


class ConnectionActivity : FragmentActivity(), LoginFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener, RegisterFragment.OnConnectionCallListener, LoginFragment.OnConnectionCallListener {

    val manager = supportFragmentManager
    val transaction: FragmentTransaction = manager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        addFragment(LoginFragment(), R.id.frame)
        Mail.visibility = View.GONE

        showAllUI()
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)
        println(APICalls.GET.Actions())
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun FragmentActivity.addFragment(fragment: Fragment, frameId: Int)  {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }


    fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment).addToBackStack(null)}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 9001) {
            ServiceConnection.handleGoogleResult(Auth.GoogleSignInApi.getSignInResultFromIntent(data), this)
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is LoginFragment) {
            fragment.SetOnFragmentInteractionListener(this)
            fragment.SetOnConnectionCallListener(this)
        }
        if (fragment is RegisterFragment) {
            fragment.SetOnFragmentInteractionListener(this)
            fragment.SetOnConnectionCallListener(this)
        }

    }

    override fun onFragmentInteraction(b : Boolean) {
        if (!b) {
            Mail.visibility = View.VISIBLE
            replaceFragment(RegisterFragment(), R.id.frame)
        } else {
            Mail.visibility = View.GONE
            replaceFragment(LoginFragment(), R.id.frame)
        }
    }

    override fun onConnectionCall(serviceNb : Int, firstConnection : Boolean) {
        ServiceConnection.fc = firstConnection
        if (serviceNb == 0) {
            if (!UserName.text.isEmpty() && !Password.text.isEmpty()) {
                if (firstConnection  && !Mail.text.isEmpty()) {
                    if (APICalls.POST.NewUser(UserName.text.toString(), Password.text.toString(), Mail.text.toString())) {
                        UserInfo.getInstance().username = UserName.text.toString();
                        UserInfo.getInstance().mail = Mail.text.toString();
                        val intent = Intent(this@ConnectionActivity, HomeActivity::class.java)
                        ContextCompat.startActivity(this@ConnectionActivity, intent, null)
                    }
                }
                if (APICalls.POST.LogUser(UserName.text.toString(), Password.text.toString())) {
                    val intent = Intent(this@ConnectionActivity, HomeActivity::class.java)
                    ContextCompat.startActivity(this@ConnectionActivity, intent, null)
                }
            }
        } else if (serviceNb == 1) {
            hideAllUI()
            startActivityForResult(ServiceConnection.GoogleAuth(this), 9001)
        } else if (serviceNb == 2) {
            hideAllUI()
            ServiceConnection.loadAuthPage(ServiceConnection.SpotifyAuth(), weebos, this@ConnectionActivity)
        }
    }

    fun hideAllUI() {
        UserName.visibility = View.GONE
        Password.visibility = View.GONE
        weebos.visibility = View.VISIBLE
        frame.visibility = View.GONE
        imageView2.visibility = View.GONE
    }

    fun showAllUI() {
        UserName.visibility = View.VISIBLE
        Password.visibility = View.VISIBLE
        weebos.visibility = View.GONE
        frame.visibility = View.VISIBLE
        imageView2.visibility = View.VISIBLE

    }

}
