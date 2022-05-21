package com.tnodev.worldnews.adapter

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayout
import java.util.*

@Suppress("DEPRECATION")
class CategoryFragmentAdapter : FragmentStatePagerAdapter {
    var tabCount = 0
    var posi = 0
    var tabLayout: TabLayout? = null
    var context: Context? = null
    var isapply = false
    var lan: String? = null
    var bitmap: Bitmap? = null

    //  ArrayList<Fragment> fragmentList=new ArrayList<>();
    private val mCurTransaction: FragmentTransaction? = null
    var fragmentManager: FragmentManager? = null

    constructor(fm: FragmentManager?) : super(fm!!) {}
    constructor(
        fragmentManager: FragmentManager?, tabCount: Int,
        tabLayout: TabLayout?,  context: Context?
    ) : super(
        fragmentManager!!
    ) {
        this.tabCount = tabCount
        this.tabLayout = tabLayout
        this.fragmentManager = fragmentManager
        this.context = context

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (position > 2) {
            super.destroyItem(container, position, `object`)
        }
    }

    override fun getItem(position: Int): Fragment {
        posi = position


//    if(position==0){
        val homeBundle = Bundle()
        when (position) {
        }


        //   return fragmentList.get(position);
        return Fragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {


        //  tv.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/NotoSansZawgyi-SemiBold.ttf"));
        val tab = tabLayout!!.getTabAt(position)
        if (tab != null) {


            // tab.setCustomView(TextView(context));
        }
        return "temstr"
    }

    override fun getCount(): Int {
        return tabCount
    }
}