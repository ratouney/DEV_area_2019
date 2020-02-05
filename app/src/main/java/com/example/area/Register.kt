package com.example.area

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class Register : FragmentActivity(), LoginFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener {

    val manager = supportFragmentManager
    val transaction: FragmentTransaction = manager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        addFragment(LoginFragment(), R.id.frame)


        /*
        weebos.visibility = View.GONE

        GoogleRegister.setOnClickListener{
            hideAllUI()
            startActivityForResult(ServiceConnection.GoogleAuth(this), 9001)
        }

        SpotifyRegister.setOnClickListener{
            hideAllUI()
            ServiceConnection.loadAuthPage(ServiceConnection.SpotifyAuth(), weebos, this@Register)
        }

        ButtonRegister.setOnClickListener{

        }

        LoginPage.setOnClickListener{
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 9001) {
            ServiceConnection.handleGoogleResult(Auth.GoogleSignInApi.getSignInResultFromIntent(data), this)
        }
    }


    fun hideAllUI() {
        ButtonRegister.visibility = View.GONE
        GoogleRegister.visibility = View.GONE
        SpotifyRegister.visibility = View.GONE
        UserNameRegister.visibility = View.GONE
        PasswordRegister.visibility = View.GONE
        textView4.visibility = View.GONE
        LoginPage.visibility = View.GONE
        weebos.visibility = View.VISIBLE
    }

*/
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun FragmentActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }


    fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment).addToBackStack(null)}
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is LoginFragment) {
            fragment.SetOnFragmentInteractionListener(this)
        }
        if (fragment is RegisterFragment) {
            fragment.SetOnFragmentInteractionListener(this)
        }
    }

    override fun onFragmentInteraction(b : Boolean) {
        if (b)
            replaceFragment(RegisterFragment(), R.id.frame)
        else
            replaceFragment(LoginFragment(), R.id.frame)
    }


}
