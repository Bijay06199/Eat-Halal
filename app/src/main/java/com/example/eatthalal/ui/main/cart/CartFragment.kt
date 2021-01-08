package com.example.eatthalal.ui.main.cart

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.andrognito.flashbar.Flashbar
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseFragment
import com.example.eatthalal.databinding.FragmentCartBinding
import com.example.eatthalal.ui.main.MainActivity
import com.example.eatthalal.ui.main.cart.adapter.ShoppingCartAdapter
import com.example.eatthalal.ui.main.cart.checkout.CheckOutActivity
import com.example.eatthalal.ui.main.cart.model.CartItemModel
import com.example.eatthalal.utils.AuthListenerInfo
import com.example.eatthalal.utils.extentions.dangerFlashBar
import com.example.eatthalal.utils.extentions.infoFlashBar
import com.example.eatthalal.utils.extentions.successFlashBar
import com.example.eatthalal.utils.extentions.warningFlashBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.card.MaterialCardView
import io.paperdb.Paper
import kotlinx.android.synthetic.main.persistent_bottom_sheet.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>(),ShoppingCartAdapter.OnItemClickListener,AuthListenerInfo {

    override fun getLayoutId(): Int = R.layout.fragment_cart
    override fun getViewModel(): CartViewModel = cartViewModel
    private val cartViewModel: CartViewModel by viewModel()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<MaterialCardView>
    lateinit var adapter: ShoppingCartAdapter
    var flashbar: Flashbar? = null
    var authListenerInfo:AuthListenerInfo?=null
    var cartItemModel:CartItemModel?=null
    var totalPrice:Double=0.0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Paper.init(requireContext())
        bottomSheetBehavior = BottomSheetBehavior.from<MaterialCardView>(persistent_bottom_sheet)

        if (savedInstanceState == null) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
        initView()
        setUpRecyclerView()
    }


    private fun setUpRecyclerView() {
        with(viewDataBinding) {
            adapter = ShoppingCartAdapter(requireContext(), ShoppingCart.getCart(),preferenceManager,this@CartFragment,this@CartFragment)

            rvCartItem.adapter = adapter
            rvCartItem.layoutManager= LinearLayoutManager(this@CartFragment.activity)
            adapter.notifyDataSetChanged()

            if (ShoppingCart.getShoppingCartSize()==0){
                clEmpty.visibility=View.VISIBLE
            }

            var totalPrice = ShoppingCart.getCart()




                .fold(0.toDouble()) { acc, cartItemModel ->
                    if(cartItemModel.product.price==""){
                        return
                    }else{
                        acc + cartItemModel.quantity.times(
                            cartItemModel.product.price!!.toDouble()
                        )
                    }

                }
            tvTotalPrice.text="${totalPrice}"
            noOfItems.text=ShoppingCart.getCart().size.toString()






        }
    }

    private fun initView() {
        with(viewDataBinding) {



            with(cartViewModel){
                cartViewModel.authListenerInfo = this@CartFragment

                btnCheckout.setOnClickListener {
                    if (noOfItems.text=="0"){
                        authListenerInfo?.onInfo("Cart is empty Cannot Proceed")
                    }
                    else
                    {
                        var intent= Intent(this@CartFragment.activity, CheckOutActivity::class.java)
                        intent.putExtra("totalPrice",totalPrice)
                        startActivity(intent)
                    }
                }

            }



            bottomSheetBehavior.setBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            amountDescription.visibility = View.VISIBLE
                            cvWithBottomSheet.visibility = View.GONE
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            amountDescription.visibility = View.GONE
                            cvWithBottomSheet.visibility = View.VISIBLE
                        }
                        BottomSheetBehavior.STATE_HALF_EXPANDED.and(20) -> {
                            amountDescription.visibility = View.GONE
                            cvWithBottomSheet.visibility = View.VISIBLE
                        }
                    }
                }

            })


            topBar.setOnClickListener {
               startActivity(Intent(this@CartFragment.activity,MainActivity::class.java))
            }
        }
    }


    companion object {
        val TAG = CartFragment::class.java.simpleName
        fun start(activity: FragmentActivity, containerId: Int) {
            val fragment = CartFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(TAG)
                .commit()
        }
    }


    override fun onSelectListener(position: Int, itemList: CartItemModel) {

        var images = itemList.product.images

        var cartItemModel = CartItemModel(itemList.product, 0)

        var _counter = cartItemModel.quantity

        for (i in 0..position){

            with(viewDataBinding){

                btnAddToCart.setOnClickListener {

                    _counter++

                    val item = CartItemModel(itemList.product)
                    ShoppingCart.addItem(item, requireContext())
                    btnAddToCart.visibility = View.GONE
                    addSubtractButton.visibility = View.VISIBLE
                    (context as MainActivity).badge.number=ShoppingCart.getCart().size
                    viewDataBinding.tvTotalPrice.text= itemList.quantity.times(itemList.product.price!!.toDouble()).toString()
                    tvCartQuantity.setText(Integer.toString(_counter))
                }


                clAdd.setOnClickListener { view ->

                    _counter++
                    val item = CartItemModel(itemList.product)
                    ShoppingCart.addItem(item, requireContext())

                    ShoppingCart.getCart()
                    (context as MainActivity).badge.number=ShoppingCart.getCart().size
                    viewDataBinding.tvTotalPrice.text= itemList.quantity.times(itemList.product.price!!.toDouble()).toString()
                    tvCartQuantity.setText(Integer.toString(_counter))


                }


                clSubtract.setOnClickListener {

                    _counter--
                    val item = CartItemModel(itemList.product)
                    ShoppingCart.removeItem(item, requireContext())
                    ShoppingCart.getCart()
                    (context as MainActivity).badge.number=ShoppingCart.getCart().size
                    tvCartQuantity.setText(Integer.toString(_counter))

                    if (_counter == 0) {
                        ShoppingCart.removeItem(item, requireContext())
                        btnAddToCart.visibility = View.VISIBLE
                        (context as MainActivity).badge.number=ShoppingCart.getCart().size
                        tvTotalPrice.text= itemList.quantity.times(itemList.product.price!!.toDouble()).toString()
                        addSubtractButton.visibility = View.GONE

                    }

                }
                btnAddToCart.visibility = View.VISIBLE
                addSubtractButton.visibility = View.GONE
            }





        }


        for (i in 0 until images!!.size) {

            var productImage = images[i]?.src
            setImageSrc(viewDataBinding.imageView5, productImage!!)
            var productName = itemList.product.name
            var description = itemList.product.description

            var price = itemList.product.price

            viewDataBinding.textView15.setText(productName)
            viewDataBinding.tvProductName.setText(productName)
            viewDataBinding.tvDescriptions.setText(Html.fromHtml(description))
            viewDataBinding.textView17.setText(price)
            viewDataBinding.tvTotalPrice.setText(price)
            viewDataBinding.tvCartAmount1.setText(price)
        }


        if (bottomSheetBehavior.state!=BottomSheetBehavior.STATE_EXPANDED){
            bottomSheetBehavior.state=BottomSheetBehavior.STATE_HALF_EXPANDED.and(20)


        }
        else{
            bottomSheetBehavior.state=BottomSheetBehavior.STATE_COLLAPSED
        }
    }


    override fun onSuccess(message: String) {
        flashbar = successFlashBar(message)
        flashbar?.show()
    }

    override fun onStarted() {

    }

    override fun onInfo(message: String) {
        flashbar = infoFlashBar(message)
        flashbar?.show()
    }

    override fun onWarning(message: String) {
        flashbar = warningFlashBar(message)
        flashbar?.show()
    }

    override fun onDanger(message: String) {
        flashbar = dangerFlashBar(message)
        flashbar?.show()
    }




}