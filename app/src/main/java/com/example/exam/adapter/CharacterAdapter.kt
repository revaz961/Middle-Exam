package com.example.exam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.Click
import com.example.exam.model.Character
import com.example.exam.databinding.CharacterListItemLayoutBinding

class CharacterAdapter() :
    PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterComparator) {

    lateinit var click: Click<Character>

    inner class CharacterViewHolder(private val binding: CharacterListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Character
        fun bind() {
            model = getItem(absoluteAdapterPosition)!!
            binding.character = model
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                click(model)
            }
        }
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    object CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character) =
            oldItem == newItem

    }
}