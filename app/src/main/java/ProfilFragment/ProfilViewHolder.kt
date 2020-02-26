package ProfilFragment

import AppFragment.MyApp
import MyLinks
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R

class ProfilViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val actionName: TextView
    private val reactionName: TextView
    private val imageAction: ImageView
    private val imageReaction: ImageView
    private val actionDesc: TextView
    private val reactionDesc: TextView
    private val apps: MutableList<MyApp> = ArrayList()

    fun bind(app: MyLinks) {
        addApp()
        actionName.setText(app.action.name)
        reactionName.setText(app.reaction.name)
        actionDesc.setText(app.action.description)
        reactionDesc.setText(app.reaction.description)
        for (items in apps) {
            if (items.text == app.action.service.name)
                imageAction.setImageResource(items.image)
            if (items.text == app.reaction.service.name)
                imageReaction.setImageResource(items.image)
        }
    }

    init {
        actionName = itemView.findViewById(R.id.actionName) as TextView
        reactionName = itemView.findViewById(R.id.reactionName) as TextView
        imageAction = itemView.findViewById(R.id.imageAction) as ImageView
        imageReaction = itemView.findViewById(R.id.imageReaction) as ImageView
        actionDesc = itemView.findViewById(R.id.actionDesc) as TextView
        reactionDesc = itemView.findViewById(R.id.reactionDesc) as TextView
    }


    private fun addApp() {
        apps.add(
            MyApp(
                "Spotify",
                R.drawable.spotify
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
    }
}