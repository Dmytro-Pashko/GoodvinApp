package com.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.R
import com.google.android.material.tabs.TabLayout
import com.presentation.ui.animatedLike.AnimatedLikeFragment
import com.presentation.ui.recycler.RecyclerFragment

/**
 * Created by Dmytro Pashko on 1/10/2019.
 */

class MainActivity : AppCompatActivity() {

    private lateinit var tabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val pager = findViewById<ViewPager>(R.id.pager).apply {
            adapter = PagerAdapter(supportFragmentManager).apply {
                tabs.add(RecyclerFragment.newInstance())
                tabs.add(AnimatedLikeFragment.newInstance())
            }
        }

        tabs = findViewById(R.id.tabs)
        tabs.setupWithViewPager(pager)
    }

    private class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        val tabs: MutableList<TabFragment> = mutableListOf()

        override fun getItem(position: Int) = tabs[position]

        override fun getCount() = tabs.size

        override fun getPageTitle(position: Int) = tabs[position].tabName
    }
}
