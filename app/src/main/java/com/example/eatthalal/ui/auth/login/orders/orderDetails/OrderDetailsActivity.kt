package com.example.eatthalal.ui.auth.login.orders.orderDetails

import android.os.Bundle
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseActivity
import com.example.eatthalal.constants.Constants
import com.example.eatthalal.databinding.ActivityOrderDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderDetailsActivity : BaseActivity<ActivityOrderDetailsBinding,OrderDetailsViewModel>() {

    override fun getLayoutId(): Int =R.layout.activity_order_details
    override fun getViewModel(): OrderDetailsViewModel =orderDetailsViewModel
     private val orderDetailsViewModel:OrderDetailsViewModel by viewModel()


    var productName:String?=null
    var quantityT:Int?=null
    var rateT:String?=null
    var order:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productName=intent.getStringExtra(Constants.Product)
        quantityT=intent.getIntExtra(Constants.Quantity,0)
        rateT=intent.getStringExtra(Constants.Price)
        order=intent.getStringExtra("order")
        initView()

    }

    private fun initView() {
        with(viewDataBinding){

            ivBack.setOnClickListener {
                finish()
            }
            var total=  quantityT?.times(rateT!!.toDouble())


            product.setText(productName)
            quantity.setText(quantityT.toString())
            rate.setText(rateT)
            orderNo.setText(order)
            tvTotalPrice.setText(rateT)
            quantityRate.setText(total.toString())


//            quantityRate.setText(Integer.parseInt(quantityT!!)*Integer.parseInt(rateT!!))

        }
    }
}