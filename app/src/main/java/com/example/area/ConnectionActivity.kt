package com.example.area

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.gms.auth.api.Auth
import kotlinx.android.synthetic.main.activity_register.*


class ConnectionActivity : FragmentActivity(), LoginFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener, RegisterFragment.OnConnectionCallListener {

    val manager = supportFragmentManager
    val transaction: FragmentTransaction = manager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        addFragment(LoginFragment(), R.id.frame)
        showAllUI()
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
        if (b)
            replaceFragment(RegisterFragment(), R.id.frame)
        else
            replaceFragment(LoginFragment(), R.id.frame)
    }

    override fun onConnectionCall(serviceNb : Int, firstConnection : Boolean) {
        if (serviceNb == 0) {
            APICalls.GET.UsersList()
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
    }

    fun showAllUI() {
        UserName.visibility = View.VISIBLE
        Password.visibility = View.VISIBLE
        weebos.visibility = View.GONE
        frame.visibility = View.VISIBLE
    }


}
