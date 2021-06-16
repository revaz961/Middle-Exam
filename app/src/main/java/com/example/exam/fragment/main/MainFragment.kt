package com.example.exam.fragment.main

import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.exam.R
import com.example.exam.adapter.ViewPagerAdapter
import com.example.exam.databinding.FragmentMainBinding
import com.example.exam.fragment.BaseFragment
import com.example.exam.fragment.character.CharacterFragment
import com.example.exam.fragment.episode.EpisodeFragment
import com.example.exam.fragment.location.LocationFragment
import com.example.exam.sharedpreferences.SharedPreferences


class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(
    FragmentMainBinding::inflate,
    MainViewModel::class.java
) {
    private var pagerAdapter: ViewPagerAdapter? = null

    override fun start() {
        init()
    }

    private fun init() {
        initViewPager()
        initBottomNavigation()
        binding.logOut.setOnClickListener {
            SharedPreferences.removeUser()
            findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
        }
    }

    private fun initViewPager() {
        val fragments = listOf(
            CharacterFragment(),
            EpisodeFragment(),
            LocationFragment()
        )
        pagerAdapter = ViewPagerAdapter(this)
        pagerAdapter!!.setFragments(fragments)
        with(binding.vpMain) {
            isUserInputEnabled = false
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.bnMain.menu.getItem(position).isChecked = true
                }
            })
        }
    }

    private fun initBottomNavigation() {
        binding.bnMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_character -> binding.vpMain.currentItem = 0
                R.id.navigation_episode -> binding.vpMain.currentItem = 1
                R.id.navigation_location -> binding.vpMain.currentItem = 2
            }
            true
        }
    }
}