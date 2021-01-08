package com.example.eatthalal.ui.main.categories.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SlidingAdapterCategory(
    var fm: FragmentManager,
    var categories:Array<String>, internal var totalTabs:Int):FragmentPagerAdapter(fm){


    override fun getItem(position: Int): Fragment {
        return Fragment()
    }

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return categories[position]
    }

//    override fun getItem(position: Int): Fragment {
//        when(position){
//            0->{
//                return  CategoriesItemFragment.start()
//            }
//            1-> return CategoriesItemFragment.start()
//            2->return CategoriesItemFragment.start()
//            3->return CategoriesItemFragment.start()
//            4->return CategoriesItemFragment.start()
//            5->return CategoriesItemFragment.start()
//            6->return CategoriesItemFragment.start()
//            else-> throw  RuntimeException("Invalid tab position")
//        }
//
//
//    }
}