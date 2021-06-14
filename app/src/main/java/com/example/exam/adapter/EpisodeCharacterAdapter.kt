package com.example.exam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.api.model.Character
import com.example.exam.databinding.CommonListItemCharacterLayoutBinding

class EpisodeCharacterAdapter(private val click: (character: Character) -> Unit) :
    RecyclerView.Adapter<EpisodeCharacterAdapter.CharacterViewHolder>() {
    private val characters = mutableListOf<Character>()
    fun setCharacter(list: List<Character>) {
        characters.addAll(list)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val binding: CommonListItemCharacterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val model = characters[absoluteAdapterPosition]
            binding.character = model
            binding.root.setOnClickListener {
                click(characters[absoluteAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CommonListItemCharacterLayoutBinding.inflate(
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