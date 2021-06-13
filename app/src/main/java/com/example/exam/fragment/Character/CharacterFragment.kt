package com.example.exam.fragment.Character

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
import com.example.exam.databinding.FragmentCharacterBinding


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

    private fun init() {
        observes()
        initRecycler()
    }

    private fun initRecycler() {
        characterAdapter = CharacterAdapter {
        }
        binding!!.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvCharacter.adapter = characterAdapter
        characterAdapter!!.addLoadStateListener {
            binding!!.progress.isVisible = it.refresh is LoadState.Loading
        }
    }

    private fun observes() {
        characterViewModel.characterList.observe(viewLifecycleOwner, {
            characterAdapter!!.submitData(lifecycle, it)
        })

        characterViewModel.characterLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ResultHandler.Success -> {
//                    if (it.data is Character) {
//                        val items = it.data
//                        d("character",items.toString())
//                        characterAdapter!!.setCharacter(listOf(items))
//                    }
                }
            }
        })

        characterViewModel.charactersLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ResultHandler.Success -> {
//                    if (it.data is PageResult<Character>) {
//                        d("Characters",it.data.results.toString())
//                        characterAdapter!!.setCharacter(it.data.results!!)
//                    }
                }
            }
        })
    }
}