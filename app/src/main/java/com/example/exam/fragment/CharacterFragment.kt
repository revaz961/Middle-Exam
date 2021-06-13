package com.example.exam.fragment

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam.CharacterViewModel
import com.example.exam.R
import com.example.exam.adapter.CharacterAdapter
import com.example.exam.api.EndPoint
import com.example.exam.api.ResultHandler
import com.example.exam.api.model.Character
import com.example.exam.api.model.PageResult
import com.example.exam.databinding.FragmentCharacterBinding
import retrofit2.Response


class CharacterFragment : Fragment() {

    private var binding: FragmentCharacterBinding? = null
    private val characterViewMode: CharacterViewModel by viewModels()
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
        characterViewMode.getResult(EndPoint.CHARACTER.getEndPoint())
    }

    private fun initRecycler() {
        characterAdapter = CharacterAdapter {
            findNavController().navigate(
                R.id.action_mainFragment_to_characterInfoFragment2,
                bundleOf("character" to it)
            )
        }
        binding!!.rvCharacter.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvCharacter.adapter = characterAdapter
        characterAdapter!!.addLoadStateListener {
            binding!!.progress.isVisible = it.refresh is LoadState.Loading
        }
    }

    private fun observes() {
        characterViewMode.characterList.observe(viewLifecycleOwner, {
            characterAdapter!!.submitData(lifecycle, it)
        })

        characterViewMode.characterLiveData.observe(viewLifecycleOwner, {
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

        characterViewMode.charactersLiveData.observe(viewLifecycleOwner, {
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