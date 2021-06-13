package com.example.exam.fragment.location

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
import com.example.exam.databinding.FragmentCharacterBinding
import com.example.exam.databinding.FragmentLocationBinding
import com.example.exam.databinding.FragmentLoginBinding


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

    private fun init(){
        observes()
        initRecycler()
    }

    private fun initRecycler(){
        locationAdapter = LocationAdapter {

        }
        binding!!.rvLocation.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvLocation.adapter = locationAdapter
    }

    private fun observes(){
        locationViewModel.locationList.observe(viewLifecycleOwner,{
            locationAdapter.submitData(lifecycle,it)
        })
    }

}