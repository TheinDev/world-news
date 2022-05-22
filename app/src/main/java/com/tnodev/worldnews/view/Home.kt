package com.tnodev.worldnews.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayout
import com.tnodev.worldnews.R
import com.tnodev.worldnews.adapter.CategoryFragmentAdapter
import com.tnodev.worldnews.databinding.ActivityHomeBinding
import java.lang.String.valueOf


class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private  var listAdapter: CategoryFragmentAdapter? = null;

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

        setUpView();

    }

    companion object{

        var pagerPos:Int = 0;
    }

    fun setUpView(){

        callPager();
        setUpTabs(binding.homePager);
    }

var itemList = listOf<String>(  "technology", "sports", "business", "science" , "health" ,"entertainment" , "general"  )
    fun callPager(){

        binding.homePager.offscreenPageLimit = 2;
        if (listAdapter == null) {
            listAdapter = CategoryFragmentAdapter(
                supportFragmentManager,
                itemList,
                binding.tabLayout.tabCount,


                binding.tabLayout,


                this
            )
            binding.homePager.setAdapter(listAdapter)
            binding.homePager.setCurrentItem(pagerPos)
        } else {
            listAdapter!!.notifyDataSetChanged()
        }
    }

    fun setUpTabs(pager: ViewPager?) {
        binding.tabLayout.setupWithViewPager(pager)
        binding.homePager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
           override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {
                pagerPos = i;

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