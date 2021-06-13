package com.example.exam.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(mainFragment: Fragment) : FragmentStateAdapter(mainFragment) {
    private val fragments = mutableListOf<Fragment>()

    fun setFragments(items:List<Fragment>){
        fragments.addAll(items)
        notifyDataSetChanged()
    }
    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}