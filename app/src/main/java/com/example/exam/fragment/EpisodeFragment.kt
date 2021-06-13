package com.example.exam.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam.CharacterViewModel
import com.example.exam.R
import com.example.exam.adapter.EpisodeAdapter
import com.example.exam.api.EndPoint
import com.example.exam.api.ResultHandler
import com.example.exam.api.model.Character
import com.example.exam.databinding.FragmentEpisodeBinding

class EpisodeFragment : Fragment() {

    private val characterViewMode: CharacterViewModel by viewModels()
    private var binding: FragmentEpisodeBinding? = null
    private lateinit var episodeAdapter:EpisodeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null){
            binding = FragmentEpisodeBinding.inflate(layoutInflater)
            init()
        }
        return binding!!.root
    }


    private fun init(){
        initRecycler()
        observes()
    }

    private fun initRecycler(){
        episodeAdapter = EpisodeAdapter {

        }
        binding!!.rvEpisode.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvEpisode.adapter = episodeAdapter
        getAllCharacter()
    }

    private fun observes(){
        characterViewMode.episodeList.observe(viewLifecycleOwner,{
            episodeAdapter.submitData(lifecycle,it)
        })

        characterViewMode.charactersLiveData.observe(viewLifecycleOwner,{
            when(it){
                is ResultHandler.Success ->{
                    val data = it.data?.results as List<Character>
                    episodeAdapter.setCharacter(data)
                }
            }
        })
    }

    private fun getAllCharacter(){
        var id = ""
        for(i in 1..671)
            id+="$i,"
        id.dropLast(1)
        characterViewMode.getResult(EndPoint.CHARACTER.getEndPoint(),id)
    }
}