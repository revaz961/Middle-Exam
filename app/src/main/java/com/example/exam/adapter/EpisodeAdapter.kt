package com.example.exam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.api.model.Character
import com.example.exam.api.model.Episode
import com.example.exam.databinding.CharacterListItemLayoutBinding
import com.example.exam.databinding.EpisodeListItemLayoutBinding

class EpisodeAdapter(private val click:(episode: Episode)->Unit) :
    PagingDataAdapter<Episode, EpisodeAdapter.EpisodeViewHolder>(EpisodeComparator) {

    inner class EpisodeViewHolder(private val binding: EpisodeListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.episode = getItem(absoluteAdapterPosition)
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

        override fun areItemsTheSame(oldItem: Episode, newItem: Episode)=
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode)=
            oldItem == newItem

    }
}