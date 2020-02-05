package AppFragment

import homeactivity.MyAdapter
import homeactivity.MyApp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R

class AppFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private val apps: MutableList<MyApp> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //setContentView(R.layout.activity_app)
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_app, container, false)
        addApp()
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = (GridLayoutManager(activity, 2))
        recyclerView?.adapter = (MyAdapter(apps, context!!))
        return root
    }

    private fun addApp() {
     apps.add(
         MyApp(
             "Facebook",
             R.drawable.facebook

         )
     )
     apps.add(
         MyApp(
             "Snapchat",
             R.drawable.snapchat
         )
     )
     apps.add(
         MyApp(
             "Spotify",
             R.drawable.spotify
         )
     )
     apps.add(
         MyApp(
             "Twitter",
             R.drawable.twitter
         )
     )
     apps.add(
         MyApp(
             "Youtube",
             R.drawable.youtube
         )
     )
     apps.add(
         MyApp(
             "Instagram",
             R.drawable.instagram
         )
     )
     apps.add(
         MyApp(
             "Messenger",
             R.drawable.messenger
         )
     )
    }
}