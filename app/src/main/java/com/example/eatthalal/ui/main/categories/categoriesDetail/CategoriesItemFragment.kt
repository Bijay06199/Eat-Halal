package com.example.eatthalal.ui.main.categories.categoriesDetail

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseFragment
import com.example.eatthalal.constants.Constants
import com.example.eatthalal.databinding.FragmentCategoriesItemBinding
import com.example.eatthalal.ui.main.cart.ShoppingCart
import com.example.eatthalal.ui.main.cart.model.CartItemModel
import com.example.eatthalal.ui.main.home.ImageActivity
import com.example.eatthalal.ui.main.home.adapter.MultiImageAdapter
import com.example.eatthalal.ui.main.shop.adapter.ProductAdapter
import com.example.eatthalal.ui.main.shop.response.Image
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.card.MaterialCardView
import io.paperdb.Paper
import kotlinx.android.synthetic.main.persistent_bottom_sheet.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class CategoriesItemFragment : BaseFragment<FragmentCategoriesItemBinding,CategoriesItemViewModel>(),ProductAdapter.OnItemClickListener,  MultiImageAdapter.OnItemClickListener{




    override fun getLayoutId(): Int =R.layout.fragment_categories_item
    override fun getViewModel(): CategoriesItemViewModel =categoriesItemViewModel
    private val categoriesItemViewModel:CategoriesItemViewModel by viewModel()

    lateinit var multiImageAdapter: MultiImageAdapter

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<MaterialCardView>
    lateinit var productAdapter: ProductAdapter
    var categoryId:Int?=null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Paper.init(requireContext())
        initView()
         categoryId=arguments?.getInt(Constants.CategoryId)
          setUpRecyclerView()

        bottomSheetBehavior=BottomSheetBehavior.from<MaterialCardView>(persistent_bottom_sheet)

        if (savedInstanceState==null){
            bottomSheetBehavior.state=BottomSheetBehavior.STATE_HIDDEN
        }

        bottomSheetBehavior.setBottomSheetCallback(object :BottomSheetBehavior.BottomSheetCallback(){
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
              when(newState){


                  BottomSheetBehavior.STATE_EXPANDED->{
                      viewDataBinding.cvWithBottomSheet.visibility=View.VISIBLE
                  }
                  BottomSheetBehavior.STATE_HIDDEN->{
                      viewDataBinding.cvWithBottomSheet.visibility=View.GONE
                  }
                  BottomSheetBehavior.STATE_HALF_EXPANDED.and(20)->{
                      viewDataBinding.cvWithBottomSheet.visibility=View.VISIBLE
                  }

              }
            }

        })
    }

    private fun initView() {
        with(viewDataBinding){
            var animation= AnimationUtils.loadAnimation(requireContext(),R.anim.rotation_anim)
            animation.setInterpolator ( LinearInterpolator() )
            progressBar5.startAnimation(animation)

            progressBar5.visibility=View.VISIBLE

        }
    }

    private fun setUpRecyclerView() {
        with(viewDataBinding){
            with(categoriesItemViewModel){

                var itemList=ArrayList<NewProductResponseItem>()
                productAdapter= ProductAdapter(this@CategoriesItemFragment,itemList)


                getCategoryWise(categoryId)
                categoryWiseEvent.observe(viewLifecycleOwner, Observer {

                    rvItems.adapter=productAdapter
                    itemList.addAll(categoryWise!!)
                    productAdapter.notifyDataSetChanged()
                    progressBar5.visibility=View.GONE

                })
            }

        }
    }

    companion object {


        val TAG = CategoriesItemFragment::class.java.simpleName
        fun start(activity: FragmentActivity, containerId: Int,id:Int?) {
            var bundle = Bundle()
            bundle.putInt(Constants.CategoryId, id!!)
            var fragment = CategoriesItemFragment()
            fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .replace(containerId,fragment)
                .addToBackStack(TAG)
                .commit()
        }
    }



    override fun onItemSelect(position: Int, itemList: NewProductResponseItem) {



        with(viewDataBinding){

            for (i in 0..position){

                var cartItemModel = CartItemModel(itemList, 0)

                var _counter = cartItemModel.quantity



                btnAddToCart.setOnClickListener {

                    _counter++

                    val item = CartItemModel(itemList)
                    ShoppingCart.addItem(item, requireContext())
                    btnAddToCart.visibility = View.GONE
                    addSubtractButton.visibility = View.VISIBLE
                    tvCartQuantity.setText(Integer.toString(_counter))
                }


                clAdd.setOnClickListener { view ->

                    _counter++
                    val item = CartItemModel(itemList)
                    ShoppingCart.addItem(item, requireContext())

                    ShoppingCart.getCart()
                    tvCartQuantity.setText(Integer.toString(_counter))


                }


                clSubtract.setOnClickListener {

                    _counter--
                    val item = CartItemModel(itemList)
                    ShoppingCart.removeItem(item, requireContext())
                    ShoppingCart.getCart()
                    tvCartQuantity.setText(Integer.toString(_counter))

                    if (_counter == 0) {
                        ShoppingCart.removeItem(item, requireContext())
                        btnAddToCart.visibility = View.VISIBLE
                        addSubtractButton.visibility = View.GONE

                    }
                }
                btnAddToCart.visibility = View.VISIBLE
                addSubtractButton.visibility = View.GONE




                var images = itemList.images

                multiImageAdapter= MultiImageAdapter(this@CategoriesItemFragment,
                    images as List<Image>
                )
                viewDataBinding.rvMultiImage.adapter=multiImageAdapter

                if (images?.size!! >1){

                    viewDataBinding.viewPagerIndicatorIconsLayout.visibility=View.VISIBLE
                }
                else{
                    viewDataBinding.viewPagerIndicatorIconsLayout.visibility=View.GONE

                }



                var productName = itemList.name
                var description = itemList.description

                if (description==""){
                    viewDataBinding.textView20.setText("")
                }
                else{
                    viewDataBinding.textView20.setText("Product Description")
                    viewDataBinding.tvDescriptions.setText(Html.fromHtml(description))
                }

                var price = itemList.price



                viewDataBinding.tvProductName1.setText(productName)
                viewDataBinding.tvProductName.setText(productName)
                viewDataBinding.tvPrice.setText(price)
                viewDataBinding.tvCartAmount.setText(price)

                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED.and(20)
                } else {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }

            }
        }

    }

    override fun onLayoutAddClick(position: Int, itemList: NewProductResponseItem) {
    }

    override fun onAddClick(position: Int, itemList: NewProductResponseItem) {
    }

    override fun onSubtractClick(position: Int, itemList: NewProductResponseItem) {
    }

    override fun onImageSelect(position: Int, itemList: Image) {


        var intent= Intent(
            this@CategoriesItemFragment.activity,
            ImageActivity::class.java
        )
        intent.putExtra("images",itemList.src)
        startActivity(intent)

    }
}