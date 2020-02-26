package homeactivity


import AppFragment.AppFragment
import AppFragment.MyApp
import ProfilFragment.ProfilFragment
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.area.R
import com.example.area.Services
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_app.*


class HomeActivity : AppCompatActivity() {

    private val apps: MutableList<MyApp> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        setSupportActionBar(bottom_app_bar)
        addFragment(AppFragment(), R.id.frame)
        val button : FloatingActionButton = findViewById(R.id.fab)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        button.setOnClickListener {
            replaceFragment(AppFragment(), R.id.frame)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottomappbar_menu, menu)
        return true
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment).addToBackStack(null)}
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {

            R.id.app_bar_profil -> {
                item.setIcon(R.drawable.profil_focus)
                replaceFragment(ProfilFragment(), R.id.frame)
            }
            R.id.app_bar_settings -> {
                replaceFragment(Services(), R.id.frame)
            }
        }
        return true
    }
}