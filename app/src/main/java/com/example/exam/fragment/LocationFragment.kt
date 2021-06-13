package com.example.exam.fragment

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.exam.CharacterViewModel
import com.example.exam.R
import com.example.exam.api.EndPoint


class LocationFragment : Fragment() {
    private val characterViewMode: CharacterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }


    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init(){
        observes()
        characterViewMode.getResult(EndPoint.LOCATION.getEndPoint())
        characterViewMode.getResult(EndPoint.LOCATION.getEndPoint(),"1,5,8,12")
    }

    private fun observes(){
        characterViewMode.locationLiveData.observe(viewLifecycleOwner,{
            d("Location","${it}")
        })

        characterViewMode.locationsLiveData.observe(viewLifecycleOwner,{
            d("Locations","${it}")
        })
    }
}