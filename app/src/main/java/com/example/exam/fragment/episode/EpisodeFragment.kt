package com.example.exam.fragment.episode

import android.app.Dialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam.adapter.EpisodeAdapter
import com.example.exam.model.Character
import com.example.exam.databinding.CharacterImageDialogLayoutBinding
import com.example.exam.databinding.FragmentEpisodeBinding
import com.example.exam.extension.init
import com.example.exam.fragment.BaseFragment

class EpisodeFragment : BaseFragment<FragmentEpisodeBinding, EpisodeViewModel>(
    FragmentEpisodeBinding::inflate,
    EpisodeViewModel::class.java
) {
    private lateinit var episodeAdapter: EpisodeAdapter


    override fun start() {
        init()
    }


    private fun init() {
        initRecycler()
        observes()
    }

    private fun initRecycler() {
        episodeAdapter = EpisodeAdapter()

        episodeAdapter.click = {
            showDialog(it)
        }

        episodeAdapter.load = { adapter, list ->
            viewModel.getCharacters(adapter, list)
        }

        binding.rvEpisode.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEpisode.adapter = episodeAdapter
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
        viewModel.episodeList.observe(viewLifecycleOwner, {
            episodeAdapter.submitData(lifecycle, it)
        })
    }
}