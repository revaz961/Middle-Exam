package com.example.exam.fragment.character

import android.app.Dialog
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam.adapter.CharacterAdapter
import com.example.exam.model.Character
import com.example.exam.databinding.CharacterImageDialogLayoutBinding
import com.example.exam.databinding.FragmentCharacterBinding
import com.example.exam.extension.init
import com.example.exam.fragment.BaseFragment


class CharacterFragment : BaseFragment<FragmentCharacterBinding, CharacterViewModel>(
    FragmentCharacterBinding::inflate,
    CharacterViewModel::class.java
) {
    private lateinit var characterAdapter: CharacterAdapter

    override fun start() {
        init()
    }

    private fun init() {
        observes()
        initRecycler()
    }

    private fun initRecycler() {
        characterAdapter = CharacterAdapter()
        characterAdapter.click = {
            showDialog(it)
        }
        with(binding.rvCharacter) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterAdapter
        }
        characterAdapter.addLoadStateListener {
            binding.progress.isVisible = it.refresh is LoadState.Loading
        }
    }

    private fun showDialog(character: Character) {
        val dialogBinding = CharacterImageDialogLayoutBinding.inflate(layoutInflater)
        dialogBinding.character = character
        val dialog = Dialog(requireContext())
        dialog.init(dialogBinding.root)
        dialog.show()
        dialogBinding.root.setOnClickListener {
            dialog.cancel()
        }
    }

    private fun observes() {
        viewModel.characterList.observe(viewLifecycleOwner, {
            characterAdapter.submitData(lifecycle, it)
        })
    }
}