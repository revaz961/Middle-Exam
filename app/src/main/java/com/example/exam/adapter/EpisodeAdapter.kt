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
import com.example.exam.model.Episode
import com.example.exam.databinding.EpisodeListItemLayoutBinding


class EpisodeAdapter() :
    PagingDataAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>(EpisodeComparator) {

    lateinit var click: Click<Character>
    lateinit var load: LoadData<String>

    inner class EpisodeViewHolder(private val binding: EpisodeListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model:Episode
        fun bind() {
            model = getItem(absoluteAdapterPosition)!!
            binding.episode = model
            val adapter = NestedCharacterAdapter()
            adapter.click = click
            val layoutManager =
                LinearLayoutManager(binding.root.context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            binding.rvCharacter.layoutManager = layoutManager
            binding.rvCharacter.adapter = adapter
            load(adapter, model.characters!!)
        }
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            EpisodeListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    object EpisodeComparator : DiffUtil.ItemCallback<Episode>() {

        override fun areItemsTheSame(oldItem: Episode, newItem: Episode) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode) =
            oldItem == newItem

    }
}