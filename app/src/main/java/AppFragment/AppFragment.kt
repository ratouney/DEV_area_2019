package AppFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R


class AppFragment() : Fragment() {
    private var recyclerView: RecyclerView? = null
    private val apps: MutableList<MyApp> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_app, container, false)
        addApp()
        MyArea.action = null
        MyArea.reaction = null
        recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = (GridLayoutManager(activity, 2))
        recyclerView?.adapter = (MyAdapter(apps, context!!))
        return root
    }

    private fun addApp() {
        apps.add(
            MyApp(
                "Gmail",
                R.drawable.gmail
            )
        )
        apps.add(
            MyApp(
                "Sheet",
                R.drawable.sheet
            )
        )
        apps.add(
            MyApp(
                "Pokemon",
                R.drawable.pokemon
            )
        )
        apps.add(
            MyApp(
                "Nasa",
                R.drawable.nasa
            )
        )
        apps.add(
            MyApp(
                "Imgur",
                R.drawable.imgur
            )
        )
        apps.add(
            MyApp(
                "Meteo",
                R.drawable.meteo
            )
        )
        apps.add(
            MyApp(
                "Meteo",
                R.drawable.meteo
            )
        )
    }
}