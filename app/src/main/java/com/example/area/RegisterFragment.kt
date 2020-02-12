package com.example.area

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private var callback: OnConnectionCallListener? = null

    fun SetOnFragmentInteractionListener(call: OnFragmentInteractionListener) {
        this.listener = call
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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onStart() {
        super.onStart()

        GoLoginPage.setOnClickListener() {
            listener?.onFragmentInteraction(true)
        }

        Register.setOnClickListener{
            callback?.onConnectionCall(0, true)
        }

        GoogleRegister.setOnClickListener{
            callback?.onConnectionCall(1, true)
        }

        SpotifyRegister.setOnClickListener{
            callback?.onConnectionCall(2, true)
        }


    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(b: Boolean)
    }

    interface OnConnectionCallListener {
        fun onConnectionCall(serviceNb: Int, firstConnection : Boolean)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
