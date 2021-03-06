package com.example.exam.fragment.location

import android.app.Dialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam.adapter.LocationAdapter
import com.example.exam.model.Character
import com.example.exam.databinding.CharacterImageDialogLayoutBinding
import com.example.exam.databinding.FragmentLocationBinding
import com.example.exam.extension.init
import com.example.exam.fragment.BaseFragment


class LocationFragment : BaseFragment<FragmentLocationBinding, LocationViewModel>(
    FragmentLocationBinding::inflate,
    LocationViewModel::class.java
) {

    private lateinit var locationAdapter: LocationAdapter

    override fun start() {
        init()
    }

    private fun init() {
        observes()
        initRecycler()
    }

    private fun initRecycler() {
        locationAdapter = LocationAdapter()

        locationAdapter.click = {
            showDialog(it)
        }

        locationAdapter.load = { adapter, list ->
            viewModel.getCharacters(adapter, list)
        }

        binding.rvLocation.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLocation.adapter = locationAdapter
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
        viewModel.locationList.observe(viewLifecycleOwner, {
            locationAdapter.submitData(lifecycle, it)
        })
    }

}