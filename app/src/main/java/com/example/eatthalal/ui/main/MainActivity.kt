package com.example.eatthalal.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import com.example.eatthalal.BuildConfig
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseActivity
import com.example.eatthalal.databinding.ActivityMainBinding
import com.example.eatthalal.ui.auth.login.LoginFragment
import com.example.eatthalal.ui.main.cart.CartFragment
import com.example.eatthalal.ui.main.cart.ShoppingCart
import com.example.eatthalal.ui.main.categories.CategoriesFragment
import com.example.eatthalal.ui.main.home.HomeFragment
import com.example.eatthalal.ui.main.shop.ShopFragment
import com.google.android.material.badge.BadgeDrawable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity() : BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun getLayoutId(): Int =R.layout.activity_main
    override fun getViewModel(): MainViewModel =mainViewModel
    private  val mainViewModel:MainViewModel by viewModel()

    lateinit var badge: BadgeDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        badge=viewDataBinding.bottomNavigation.getOrCreateBadge(R.id.page_3)
        badge.isVisible=true
        badge.number=ShoppingCart.getCart().size

        initView()
//        if (!preferenceManager.getIsLoggedIn()){
//            Toast.makeText(this,"You are logged out",Toast.LENGTH_LONG).show()
//        }
//        if (preferenceManager!!.getIsLoggedIn()){
//            Toast.makeText(this,"You are log in",Toast.LENGTH_LONG).show()
//
//        }
        bottomNavigationItem()

        if(savedInstanceState==null){

                HomeFragment.start(this@MainActivity,R.id.main_screen_container)

        }

    }



    private fun initView() {
        with(viewDataBinding){


            fabButton.setOnClickListener {
                ShopFragment.start(this@MainActivity,R.id.main_screen_container)
            }
        }
    }

    fun isOnline(): Boolean {
        val cm =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null &&
                cm.activeNetworkInfo!!.isConnectedOrConnecting
    }

    private fun bottomNavigationItem() {
        with(viewDataBinding){

            bottomNavigation.setOnNavigationItemSelectedListener { item ->

                when(item.itemId){
                    R.id.page_1->{
                        HomeFragment.start(this@MainActivity,R.id.main_screen_container)
                        true
                    }
                    R.id.page_2->{
                        CategoriesFragment.start(this@MainActivity,R.id.main_screen_container)
                        true
                    }
                    R.id.page_3->{
                        CartFragment.start(this@MainActivity,R.id.main_screen_container)
                        true
                    }


                    R.id.page_4->{
                        LoginFragment.start(this@MainActivity,R.id.main_screen_container)
                        true
                    }
                    else -> false
                }

            }


        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (BuildConfig.DEBUG && data == null) {
                error("Assertion failed")

            }
            var badge = viewDataBinding.bottomNavigation.getOrCreateBadge(R.id.page_3)
            badge.isVisible = true
            badge.number = ShoppingCart.getCart().size



        }


    }
}