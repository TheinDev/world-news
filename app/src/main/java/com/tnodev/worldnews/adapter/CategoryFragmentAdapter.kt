package com.tnodev.worldnews.adapter

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import com.tnodev.worldnews.view.NewsFragment

@Suppress("DEPRECATION")
class CategoryFragmentAdapter : FragmentStatePagerAdapter {
    var tabCount = 0
    var posi = 0
    var tabLayout: TabLayout? = null
    var context: Context? = null

    var itemList = listOf<String>()


    private val mCurTransaction: FragmentTransaction? = null
    var fragmentManager: FragmentManager? = null

    constructor(fm: FragmentManager?) : super(fm!!) {}
    constructor(
        fragmentManager: FragmentManager?, itemList: List<String>, tabCount: Int,
        tabLayout: TabLayout?, context: Context?
    ) : super(
        fragmentManager!!
    ) {
        this.tabCount = tabCount
        this.tabLayout = tabLayout
        this.fragmentManager = fragmentManager
        this.context = context
        this.itemList = itemList;

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (position > 2) {
            super.destroyItem(container, position, `object`)
        }
    }

    override fun getItem(position: Int): Fragment {
        posi = position


        val data = Bundle()
        data.putString("item", itemList[position]);
        var view = NewsFragment();
        view.arguments = data;

        return view;


    }


    override fun getPageTitle(position: Int): CharSequence? {


        val tab = tabLayout!!.getTabAt(position)
        if (tab != null) {


            tab.customView = TextView(context);
        }
        return itemList[position]
    }

    override fun getCount(): Int {
        return itemList.size;
    }
}