package com.example.eatthalal.ui.main.home.offers

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.lifecycle.Observer
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseActivity
import com.example.eatthalal.databinding.ActivityFeaturedProductBinding
import com.example.eatthalal.ui.main.MainActivity
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

class OffersActivity : BaseActivity<ActivityFeaturedProductBinding,OffersViewModel>(),
    ProductAdapter.OnItemClickListener,
   MultiImageAdapter.OnItemClickListener
{

    override fun getLayoutId(): Int =R.layout.activity_featured_product
    override fun getViewModel(): OffersViewModel =offersViewModel
    private val offersViewModel:OffersViewModel by viewModel()

    lateinit var featuredProductAdapter: ProductAdapter
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<MaterialCardView>
    lateinit var multiImageAdapter:MultiImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        initView()
        setUpRecyclerView()
        bottomSheetBehavior= BottomSheetBehavior.from<MaterialCardView>(persistent_bottom_sheet)

        if (savedInstanceState==null){
            bottomSheetBehavior.state= BottomSheetBehavior.STATE_HIDDEN
        }

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){


                    BottomSheetBehavior.STATE_EXPANDED->{
                        viewDataBinding.cvWithBottomSheet.visibility= View.VISIBLE
                    }
                    BottomSheetBehavior.STATE_HIDDEN->{
                        viewDataBinding.cvWithBottomSheet.visibility= View.GONE
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED.and(20)->{
                        viewDataBinding.cvWithBottomSheet.visibility= View.VISIBLE
                    }

                }
            }

        })


    }

    private fun setUpRecyclerView() {
        with(viewDataBinding){
            with(offersViewModel){

                var productList = ArrayList<NewProductResponseItem>()


                featuredProductAdapter = ProductAdapter(this@OffersActivity, productList)
                getFeaturedProduct()
                featuredProductEvent.observe(this@OffersActivity, Observer {

                    recyclerViewAll.adapter = featuredProductAdapter
                    productList.addAll(productName!!)
                    featuredProductAdapter.notifyDataSetChanged()
                    progressBar4.visibility=View.GONE
                })
            }
        }
    }

    private fun initView() {
        with(viewDataBinding){

            var animation= AnimationUtils.loadAnimation(this@OffersActivity,R.anim.rotation_anim)
            animation.setInterpolator ( LinearInterpolator() )
            progressBar4.startAnimation(animation)

            ivBack.setOnClickListener {
                startActivity(Intent(this@OffersActivity, MainActivity::class.java))

            }
        }
    }

    override fun onItemSelect(position: Int, itemList: NewProductResponseItem) {

        with(viewDataBinding){

            for (i in 0 ..position){

                var cartItemModel = CartItemModel(itemList, 0)

                var _counter = cartItemModel.quantity



                btnAddToCart.setOnClickListener {

                    _counter++

                    val item = CartItemModel(itemList)
                    ShoppingCart.addItem(item, this@OffersActivity)
                    btnAddToCart.visibility = View.GONE
                    addSubtractButton.visibility = View.VISIBLE
                    tvCartQuantity.setText(Integer.toString(_counter))
                    notifyChange()
                }


                clAdd.setOnClickListener { view ->

                    _counter++
                    val item = CartItemModel(itemList)
                    ShoppingCart.addItem(item, this@OffersActivity)

                    ShoppingCart.getCart()
                    tvCartQuantity.setText(Integer.toString(_counter))
                    notifyChange()


                }


                clSubtract.setOnClickListener {

                    _counter--
                    val item = CartItemModel(itemList)
                    ShoppingCart.removeItem(item, this@OffersActivity)
                    ShoppingCart.getCart()
                    tvCartQuantity.setText(Integer.toString(_counter))

                    if (_counter == 0) {
                        ShoppingCart.removeItem(item, this@OffersActivity)
                        btnAddToCart.visibility = View.VISIBLE
                        addSubtractButton.visibility = View.GONE

                    }
                    notifyChange()
                }



                var images = itemList.images
                multiImageAdapter= MultiImageAdapter(this@OffersActivity,
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
                    viewDataBinding.tvDescriptions.setText(Html.fromHtml(description))
                }
                else{
                    viewDataBinding.textView20.setText("Product Descriptions")
                    viewDataBinding.tvDescriptions.setText(Html.fromHtml(description))
                }

                var price = itemList.price

                viewDataBinding.tvProductName1.setText(productName)
                viewDataBinding.tvProductName.setText(productName)
                viewDataBinding.tvDescriptions.setText(Html.fromHtml(description))
                viewDataBinding.tvPrice.setText(price)
                viewDataBinding.tvTotalAmount.setText(price)

                if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED.and(20)
                } else {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

            btnAddToCart.visibility = View.VISIBLE
            addSubtractButton.visibility = View.GONE


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
            this,
            ImageActivity::class.java
        )
        intent.putExtra("images",itemList.src)
        startActivity(intent)
    }

}