package com.example.monitoreo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.monitoreo.adapters.MyPagerAdapter
import com.example.monitoreo.R
import com.google.android.material.tabs.TabLayout

class Menu : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        toolBar.setTitle("TUTOR")
        val bundle = Bundle(getIntent().extras)
        val fragmentAdapter = MyPagerAdapter(supportFragmentManager, bundle)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = fragmentAdapter
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
    }
}