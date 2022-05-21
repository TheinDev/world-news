package com.tnodev.worldnews

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayout
import com.tnodev.worldnews.databinding.ActivityHomeBinding
import java.lang.String.valueOf


class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

      //  val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )


        navView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            item ->
            when(item.itemId){

                R.id.navigation_home -> {

                    binding.homeLayout.visibility = View.VISIBLE

                }




            }

            true;
        });


    }

    companion object{

        var catpos:Int = 0;
    }

    fun setUpTabs(pager: ViewPager?) {
        binding.tabLayout.setupWithViewPager(pager)
        binding.homePager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
           override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {
                catpos = i;

            }

          override  fun onPageScrollStateChanged(i: Int) {}
        })

        binding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
          override  fun onTabSelected(tab: TabLayout.Tab) {
                binding.homePager.setCurrentItem(tab.getPosition())
                Log.d(
                    "TAB SELECTION",
                    "TAB POSITion is " + "  " + valueOf(tab.getPosition()) + "   TAB POS"
                )
            }

           override fun onTabUnselected(tab: TabLayout.Tab?) {}
           override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

}