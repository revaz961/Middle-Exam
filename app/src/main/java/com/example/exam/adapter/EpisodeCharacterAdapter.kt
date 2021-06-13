package com.example.exam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.api.model.Character
import com.example.exam.databinding.EpisodeListItemCharacterLayoutBinding

class EpisodeCharacterAdapter :
    RecyclerView.Adapter<EpisodeCharacterAdapter.CharacterViewHolder>() {
    private val characters = mutableListOf<Character>()
    fun setCharacter(list: List<Character>) {
        characters.addAll(list)
    }

    inner class CharacterViewHolder(private val binding: EpisodeListItemCharacterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.character = characters[absoluteAdapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            EpisodeListItemCharacterLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = characters.size
}