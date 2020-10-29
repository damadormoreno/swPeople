package com.davidups.starwars.features.movies.view.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidups.skell.R
import com.davidups.skell.core.extensions.inflate
import com.davidups.skell.core.extensions.loadFromUrl
import com.davidups.starwars.core.extensions.randomImage
import com.davidups.starwars.features.movies.models.view.MovieView
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.item_person_row.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    internal var collection: List<MovieView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (MovieView) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_person_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], clickListener)
    }

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: MovieView, clickListener: (MovieView) -> Unit) {
            itemView.ivBanner.loadFromUrl(String.randomImage())
            itemView.tvName.text = movie.title
            itemView.cvPerson.setOnClickListener {
                clickListener(movie)
            }
        }
    }
}
