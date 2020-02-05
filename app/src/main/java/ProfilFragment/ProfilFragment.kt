import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.LightingColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.area.R


class ProfilFragment : Fragment() {
    /*private var recyclerView: RecyclerView? = null
    private val apps: MutableList<MyApp> = ArrayList()*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_profil, container, false)
        //addApp()
        /*recyclerView = root.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = (GridLayoutManager(activity, 2))
        recyclerView?.adapter = (MyAdapter(apps, activity!!.applicationContext))*/
        return root
    }
}