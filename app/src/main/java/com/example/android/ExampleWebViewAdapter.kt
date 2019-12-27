package com.example.android

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ExampleWebViewAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabs = arrayListOf<ExampleWebViewFragment>().apply {
        add(ExampleWebViewFragment())
        add(ExampleWebViewFragment())
    }

    override fun getItem(position: Int): Fragment = tabs[position]

    override fun getCount(): Int = tabs.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "1"
            1 -> "2"
            else -> null
        }
    }
}