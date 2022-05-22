package com.tnodev.worldnews.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.ui.AppBarConfiguration
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayout
import com.tnodev.worldnews.R
import com.tnodev.worldnews.adapter.CategoryFragmentAdapter
import com.tnodev.worldnews.adapter.MainAdapter
import com.tnodev.worldnews.databinding.ActivityHomeBinding
import com.tnodev.worldnews.repository.NewsDatabase
import com.tnodev.worldnews.repository.NewsRepo
import com.tnodev.worldnews.util.AppCons.Companion.detailArticle
import com.tnodev.worldnews.viewmodel.MainViewModel
import com.tnodev.worldnews.viewmodel.MyViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.String.valueOf


class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private  var listAdapter: CategoryFragmentAdapter? = null;
    var adapter = MainAdapter();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveRecyclerview.adapter  = adapter;
        val navView: BottomNavigationView = binding.navView

      //  val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        val newsRepository = NewsRepo(NewsDatabase(applicationContext))

      var  viewModel: MainViewModel =  ViewModelProvider(this, MyViewModelFactory(newsRepository))
            .get(MainViewModel::class.java);
        navView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            item ->
            when(item.itemId){

                R.id.navigation_home -> {

                    binding.homeLayout.visibility = View.VISIBLE
                    binding.saveLayout.visibility = View.INVISIBLE;

                }


                R.id.navigation_notifications ->{

                    binding.homeLayout.visibility = View.INVISIBLE;
                    binding.saveLayout.visibility = View.VISIBLE;

                    lifecycle.coroutineScope.launch {

                        viewModel.getSavedNews().collect {
                            it.let {
                                Log.d("detailview>> ",it[0].title ?: "");
                                adapter.setNewsItems(it);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }

                }



            }

            true;
        });



        setUpView();


        adapter.setOnItemClickListener {

            it.let {

              detailArticle = it;

                val intent = Intent(this , DetailView::class.java);
                startActivity(intent);
            }


        }





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