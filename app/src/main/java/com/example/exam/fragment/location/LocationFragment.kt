package com.example.exam.fragment.location

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exam.fragment.Character.CharacterViewModel
import com.example.exam.R
import com.example.exam.adapter.CharacterAdapter
import com.example.exam.adapter.EpisodeAdapter
import com.example.exam.adapter.LocationAdapter
import com.example.exam.api.model.Character
import com.example.exam.databinding.CharacterImageDialogLayoutBinding
import com.example.exam.databinding.FragmentCharacterBinding
import com.example.exam.databinding.FragmentLocationBinding
import com.example.exam.databinding.FragmentLoginBinding
import com.example.exam.extension.init


class LocationFragment : Fragment() {

    private var binding: FragmentLocationBinding? = null
    private lateinit var locationAdapter: LocationAdapter
    private val locationViewModel: LocationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentLocationBinding.inflate(layoutInflater)
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
        locationAdapter = LocationAdapter({
            showDialog(it)
        }) { adapter, list ->
            locationViewModel.getCharacters(adapter, list)
        }
        binding!!.rvLocation.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvLocation.adapter = locationAdapter
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
        locationViewModel.locationList.observe(viewLifecycleOwner, {
            locationAdapter.submitData(lifecycle, it)
        })
    }

}