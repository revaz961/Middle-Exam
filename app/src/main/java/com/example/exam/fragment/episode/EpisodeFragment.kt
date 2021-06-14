package com.example.exam.fragment.episode

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam.adapter.EpisodeAdapter
import com.example.exam.api.ResultHandler
import com.example.exam.api.model.Character
import com.example.exam.databinding.CharacterImageDialogLayoutBinding
import com.example.exam.databinding.FragmentEpisodeBinding
import com.example.exam.extension.init

class EpisodeFragment : Fragment() {

    private val episodeViewModel: EpisodeViewModel by viewModels()
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    private fun init(){
        initRecycler()
        observes()
    }

    private fun initRecycler(){
        episodeAdapter = EpisodeAdapter({
            showDialog(it)
        }) { adapter, list ->
            episodeViewModel.getCharacters(adapter,list)
        }
        binding!!.rvEpisode.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvEpisode.adapter = episodeAdapter
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

    private fun observes(){
        episodeViewModel.episodeList.observe(viewLifecycleOwner,{
            episodeAdapter.submitData(lifecycle,it)
        })
    }
}