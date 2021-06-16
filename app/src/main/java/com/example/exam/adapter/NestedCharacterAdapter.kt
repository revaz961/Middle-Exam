package com.example.exam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.Click
import com.example.exam.model.Character
import com.example.exam.databinding.CommonListItemCharacterLayoutBinding

class NestedCharacterAdapter() :
    RecyclerView.Adapter<NestedCharacterAdapter.CharacterViewHolder>() {

    lateinit var click: Click<Character>

    private val characters = mutableListOf<Character>()

    fun setCharacter(list: List<Character>) {
        characters.addAll(list)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val binding: CommonListItemCharacterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: Character

        fun bind() {
            model = characters[absoluteAdapterPosition]
            binding.character = model
            binding.root.setOnClickListener {
                click(model)
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