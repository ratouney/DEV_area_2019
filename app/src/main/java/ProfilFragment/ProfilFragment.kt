package ProfilFragment

import MyLinks
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.area.APICalls
import com.google.gson.Gson


class ProfilFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    var links: List<MyLinks> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val root = inflater.inflate(R.layout.fragment_profil, container, false)
        val gson = Gson()
        var objectArrayString: String? = null
        objectArrayString = APICalls.GET.Area()
        links = gson.fromJson(objectArrayString, Array<MyLinks>::class.java).toList()
        recyclerView = root.findViewById(R.id.recyclerViewArea)
        recyclerView?.layoutManager = (LinearLayoutManager(context))
        recyclerView?.adapter = (ProfilAdapter(links, context!!))
        return root
    }
}