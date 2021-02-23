package com.davidups.starwars.features.people.view.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.davidups.skell.R
import com.davidups.skell.core.extensions.inflate
import com.davidups.skell.core.extensions.loadFromUrl
import com.davidups.starwars.core.extensions.randomImage
import com.davidups.starwars.features.people.data.models.view.PersonView
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.item_person_row.view.*
import okhttp3.internal.notify

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    internal var collection: List<PersonView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (PersonView) -> Unit = { }
    internal var clickFavListener: (PersonView) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_person_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], clickListener, clickFavListener)
    }

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(person: PersonView, clickListener: (PersonView) -> Unit, clickFavListener: (PersonView) -> Unit) {
            itemView.ivBanner.loadFromUrl(person.urlImage)
            itemView.tvName.text = person.name
            when(person.isFavorite){
                true -> itemView.favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                false -> itemView.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
            itemView.cvPerson.setOnClickListener {
                clickListener(person)
            }
            itemView.favorite.setOnClickListener {
                clickFavListener(person)
            }
        }
    }
}
