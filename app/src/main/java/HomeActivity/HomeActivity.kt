package HomeActivity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R


class HomeActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private val apps: MutableList<MyApp> = ArrayList()

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        addApp()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = (GridLayoutManager(this, 2))
        recyclerView?.adapter = (MyAdapter(apps, this))
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