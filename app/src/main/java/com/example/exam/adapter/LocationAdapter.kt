package com.example.exam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.api.model.Character
import com.example.exam.api.model.Episode
import com.example.exam.api.model.Location
import com.example.exam.databinding.EpisodeListItemLayoutBinding
import com.example.exam.databinding.LocationListItemLayoutBinding

class LocationAdapter(private val click: (locatio: Location) -> Unit) :
    PagingDataAdapter<Location, LocationAdapter.LocationViewHolder>(LocationComparator) {

    private val characters = mutableListOf<Character>()
    fun setCharacter(list: List<Character>) {
        characters.addAll(list)
    }

    inner class LocationViewHolder(private val binding: LocationListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val model = getItem(absoluteAdapterPosition)
            binding.location = model
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind()
    }

    object LocationComparator : DiffUtil.ItemCallback<Location>() {

        override fun areItemsTheSame(oldItem: Location, newItem: Location) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Location, newItem: Location) =
            oldItem == newItem
    }

}