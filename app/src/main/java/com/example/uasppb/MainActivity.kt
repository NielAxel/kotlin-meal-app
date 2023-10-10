package com.example.uasppb

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.uasppb.databinding.ActivityMainBinding
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomBars()
        initNavHost()
    }

    private fun initBottomBars() {
        binding.bottomNavigation.setOnTabSelectListener(object :
            AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                Log.d("TAB_SELECTED", "Selected index: $newIndex, title: ${newTab.title}")
                when (newIndex) {
                    0, 1 -> navController.navigate(getNavDestinationId(newIndex))
                }
            }

            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
                Log.d("TAB_RESELECTED", "Reselected index: $index, title: ${tab.title}")
            }
        })
    }

    private fun initNavHost() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.visibility =
                if (isBottomBarVisible(destination.id)) View.VISIBLE else View.GONE
        }
    }

    private fun isBottomBarVisible(destinationId: Int): Boolean {
        return destinationId == R.id.homeFragment || destinationId == R.id.profileFragment
    }

    private fun getNavDestinationId(index: Int): Int {
        return when (index) {
            0 -> R.id.homeFragment
            1 -> R.id.profileFragment
            else -> throw IllegalArgumentException("Invalid index: $index")
        }
    }
}
