package com.example.exam.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.exam.R
import com.example.exam.adapter.ViewPagerAdapter
import com.example.exam.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    var binding: FragmentMainBinding? = null
    var pagerAdapter: ViewPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(binding == null){
            binding = FragmentMainBinding.inflate(layoutInflater)
            init()
        }
        return binding!!.root
    }

    private fun init() {
        initViewPager()
        initBottomNavigation()
    }

    private fun initViewPager() {
        val fragments = listOf(
            CharacterFragment(),
            EpisodeFragment(),
            LocationFragment()
        )
        pagerAdapter = ViewPagerAdapter(this)
        pagerAdapter!!.setFragments(fragments)
        binding!!.vpMain.adapter = pagerAdapter
        binding!!.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding!!.bnMain.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun initBottomNavigation(){
        binding!!.bnMain.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_character -> binding!!.vpMain.currentItem = 0
                R.id.navigation_episode -> binding!!.vpMain.currentItem = 1
                R.id.navigation_location -> binding!!.vpMain.currentItem = 2
            }
            true
        }
    }
}