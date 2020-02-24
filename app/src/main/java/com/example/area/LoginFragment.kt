package com.example.area

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private var callback: OnConnectionCallListener? = null


    fun SetOnFragmentInteractionListener(callback: OnFragmentInteractionListener) {
        this.listener = callback
    }


    fun SetOnConnectionCallListener(call: OnConnectionCallListener) {
        this.callback = call
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        GoRegisterPage.setOnClickListener() {
            listener?.onFragmentInteraction(false)
        }

        Login.setOnClickListener{
            callback?.onConnectionCall(0, false)
        }

        GoogleLogin.setOnClickListener{
            callback?.onConnectionCall(1, false)
        }

        SpotifyLogin.setOnClickListener{
            callback?.onConnectionCall(2, false)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }else if (context is OnConnectionCallListener) {
            callback = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        callback = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(b: Boolean)
    }

    interface OnConnectionCallListener {
        fun onConnectionCall(serviceNb: Int, firstConnection : Boolean)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
