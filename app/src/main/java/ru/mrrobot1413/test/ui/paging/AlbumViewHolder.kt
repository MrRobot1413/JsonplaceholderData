package ru.mrrobot1413.test.ui.paging

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mrrobot1413.test.R
import ru.mrrobot1413.test.network.models.Album

class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val userId = itemView.findViewById<TextView>(R.id.user_id_txt)
    private val id = itemView.findViewById<TextView>(R.id.id_txt)
    private val title = itemView.findViewById<TextView>(R.id.title_txt)

    @SuppressLint("SetTextI18n")
    fun bind(album: Album){
        userId.text = itemView.context.getString(R.string.user_id) + " " + album.userId.toString()
        id.text = itemView.context.getString(R.string.id) + " " + album.id.toString()
        title.text = itemView.context.getString(R.string.title) + " " + album.title
    }
}