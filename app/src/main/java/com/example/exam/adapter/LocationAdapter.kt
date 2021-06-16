package com.example.exam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.Click
import com.example.exam.LoadData
import com.example.exam.model.Character
import com.example.exam.model.Location
import com.example.exam.databinding.LocationListItemLayoutBinding

class LocationAdapter() :
    PagingDataAdapter<Location, LocationAdapter.LocationViewHolder>(LocationComparator) {

    lateinit var click: Click<Character>
    lateinit var load: LoadData<String>

    inner class LocationViewHolder(private val binding: LocationListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: Location

        fun bind() {
            model = getItem(absoluteAdapterPosition)!!
            binding.location = model

            val adapter = NestedCharacterAdapter()
            adapter.click = click

            val layoutManager =
                LinearLayoutManager(binding.root.context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            binding.rvCharacter.layoutManager = layoutManager
            binding.rvCharacter.adapter = adapter

            load(adapter, model.residents!!)
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