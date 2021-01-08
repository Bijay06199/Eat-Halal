package com.example.eatthalal.ui.main.categories.categoriesDetail

import android.os.Bundle
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseActivity
import com.example.eatthalal.constants.Constants
import com.example.eatthalal.databinding.ActivityCategoriesDetailBinding
import com.example.eatthalal.ui.main.categories.response.CategoriesResponse
import com.google.android.material.tabs.TabLayout
import io.paperdb.Paper
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesDetailActivity :
    BaseActivity<ActivityCategoriesDetailBinding, CategoriesDetailViewModel>() {

    var tab: Int = 0

    override fun getLayoutId(): Int = R.layout.activity_categories_detail
    override fun getViewModel(): CategoriesDetailViewModel = categoriesDetailViewModel
    private val categoriesDetailViewModel: CategoriesDetailViewModel by viewModel()
    var categoryName: String? = null
    var allCategoryName: String? = null
    var categoryId: Int? = null
    var totalCategoriesList: Array<String>? = null
    var categoriesList = ArrayList<CategoriesResponse>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        categoryName = intent.getStringExtra(Constants.CategoryName)
        categoryId = intent.getIntExtra(Constants.CategoryId, 0)

        if (savedInstanceState == null) {
            CategoriesItemFragment.start(
                this@CategoriesDetailActivity,
                R.id.categories_container,
                categoryId
            )

        }
        initView()
        //  Toast.makeText(this,"Category id is"+categoryId,Toast.LENGTH_LONG).show()
        setUpTabLayout()
        tabListener()


    }

    private fun tabListener() {
        with(viewDataBinding) {
//            viewpagerHeadlines.addOnPageChangeListener(
//                TabLayout.TabLayoutOnPageChangeListener(
//                    tablayoutHeadlines
//                )
//            )

            tablayoutHeadlines.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    //  viewpagerHeadlines.currentItem=tab!!.position
                    when (tab?.position) {
                        0 -> {

                            CategoriesItemFragment.start(
                                this@CategoriesDetailActivity,
                                R.id.categories_container, categoryId
                            )

                        }
                        1 -> {

                            CategoriesItemFragment.start(
                                this@CategoriesDetailActivity,
                                R.id.categories_container, 23
                            )

                        }
                        2 -> {

                            CategoriesItemFragment.start(
                                this@CategoriesDetailActivity,
                                R.id.categories_container, 21
                            )
                        }
                    }
                }


            })

            if (categoryName == "Eat Halal Menu") {
                tablayoutHeadlines.getTabAt(0)?.setText(categoryName)
                tablayoutHeadlines.getTabAt(1)?.setText("Offers")
                tablayoutHeadlines.getTabAt(2)?.setText("Today's Special")
            }

            if (categoryName == "Offers") {
                tablayoutHeadlines.getTabAt(0)?.setText(categoryName)
                tablayoutHeadlines.getTabAt(1)?.setText("Eat Halal Menu")
                tablayoutHeadlines.getTabAt(2)?.setText("Today's Special")
            }

            if (categoryName == "Today's Special") {
                tablayoutHeadlines.getTabAt(0)?.setText(categoryName)
                tablayoutHeadlines.getTabAt(1)?.setText("Offers")
                tablayoutHeadlines.getTabAt(2)?.setText("Eat Halal Menu")
            }


        }
    }

    private fun setUpTabLayout() {
        with(categoriesDetailViewModel) {


        }

//        val viewPager=SlidingAdapterCategory(
//            this.supportFragmentManager,Constants.categories,
//            Constants.categories.size
//        )
        with(viewDataBinding) {
//          viewpagerHeadlines.adapter=viewPager
//           tablayoutHeadlines.setupWithViewPager(viewpagerHeadlines)
//            viewpagerHeadlines.setCurrentItem(tab,true)
//            viewpagerHeadlines.offscreenPageLimit=0


        }


    }


    private fun initView() {
        with(viewDataBinding) {

            tvCategoryName.setText(categoryName)
            topBar.setOnClickListener {
                finish()
            }
        }
    }

//    companion object {
//        private fun getCategoryId(categoriesDetailActivity: CategoriesDetailActivity): Int? {
//            return categoriesDetailActivity.categoryId
//        }
}
