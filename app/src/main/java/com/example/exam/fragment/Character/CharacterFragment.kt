package com.example.exam.fragment.Character

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam.adapter.CharacterAdapter
import com.example.exam.api.ResultHandler
import com.example.exam.api.model.Character
import com.example.exam.databinding.CharacterImageDialogLayoutBinding
import com.example.exam.databinding.FragmentCharacterBinding
import com.example.exam.extension.init


class CharacterFragment : Fragment() {

    private var binding: FragmentCharacterBinding? = null
    private val characterViewModel: CharacterViewModel by viewModels()
    private var characterAdapter: CharacterAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentCharacterBinding.inflate(layoutInflater)
            init()
        }
        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun init() {
        observes()
        initRecycler()
    }

    private fun initRecycler() {
        characterAdapter = CharacterAdapter {
            showDialog(it)
        }
        binding!!.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvCharacter.adapter = characterAdapter
        characterAdapter!!.addLoadStateListener {
            binding!!.progress.isVisible = it.refresh is LoadState.Loading
        }
    }

    private fun showDialog(character: Character){
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
        characterViewModel.characterList.observe(viewLifecycleOwner, {
            characterAdapter!!.submitData(lifecycle, it)
        })
    }
}