package com.example.eatthalal.ui.main.cart.checkout

import android.os.Bundle
import com.andrognito.flashbar.Flashbar
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseActivity
import com.example.eatthalal.databinding.ActivityCheckOutBinding
import com.example.eatthalal.ui.main.cart.ShoppingCart
import com.example.eatthalal.utils.AuthListenerInfo
import com.example.eatthalal.utils.extentions.dangerFlashBar
import com.example.eatthalal.utils.extentions.infoFlashBar
import com.example.eatthalal.utils.extentions.successFlashBar
import com.example.eatthalal.utils.extentions.warningFlashBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckOutActivity : BaseActivity<ActivityCheckOutBinding,CheckOutViewModel>(),AuthListenerInfo {
    override fun getLayoutId(): Int =R.layout.activity_check_out
    override fun getViewModel(): CheckOutViewModel =checkOutViewModel
    private val checkOutViewModel:CheckOutViewModel by viewModel()


    var flashbar:Flashbar?=null
    var totalPrice:String?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {

        with(viewDataBinding){
            eTFirstName.setText(preferenceManager.getFirstName())
            eTLastName.setText(preferenceManager.getLastName())
            eTMailAddress.setText(preferenceManager.getEmail())
            eTContactNumber.setText(preferenceManager.getNumber())
            eTAddress1.setText(preferenceManager.getAddress())
            eTAddress2.setText(preferenceManager.getAddress())

            tVItemCount.setText(ShoppingCart.getCart().size.toString())



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
            tVTotalAmt.text="RS ${totalPrice}"


        }


        checkOutViewModel.authListenerInfo=this

    }


    override fun onSuccess(message: String) {

        flashbar=successFlashBar(message)
        flashbar?.show()

    }

    override fun onStarted() {


    }

    override fun onInfo(message: String) {
        flashbar=infoFlashBar(message)
        flashbar?.show()

    }

    override fun onWarning(message: String) {
        flashbar=warningFlashBar(message)
        flashbar?.show()

    }

    override fun onDanger(message: String) {
        flashbar=dangerFlashBar(message)
        flashbar?.show()

    }
}