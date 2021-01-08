package com.example.eatthalal.ui.auth.login.orders

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseActivity
import com.example.eatthalal.constants.Constants
import com.example.eatthalal.databinding.ActivityCustomerOrderBinding
import com.example.eatthalal.ui.auth.login.orders.orderDetails.OrderDetailsActivity
import com.example.eatthalal.ui.auth.login.response.CustomerOrderResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomerOrderActivity : BaseActivity<ActivityCustomerOrderBinding,CustomerOrderViewModel>(),CustomerOrderAdapter.OnItemClickListener {

    override fun getLayoutId(): Int =R.layout.activity_customer_order
    override fun getViewModel(): CustomerOrderViewModel=customeOrderViewModel
    private val customeOrderViewModel:CustomerOrderViewModel by viewModel()


   lateinit var customerOrderAdapter: CustomerOrderAdapter
    var itemList = ArrayList<CustomerOrderResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        with(viewDataBinding){
            with(customeOrderViewModel){

                customerOrderAdapter= CustomerOrderAdapter(this@CustomerOrderActivity,itemList)
                rvCustomerOrders.adapter=customerOrderAdapter

                getOrders(preferenceManager.getCustomerId())
                orderEvent.observe(this@CustomerOrderActivity, Observer {

                    itemList.addAll(orders!!)
                    customerOrderAdapter.notifyDataSetChanged()
                    progressBar6.visibility= View.GONE

                })

            }
        }
    }

    private fun initView() {
        with(viewDataBinding){

            ivBack.setOnClickListener {
                finish()
            }
        }
    }

    override fun onItemSelect(position: Int, itemList: CustomerOrderResponse) {

        var lineItems=itemList.lineItems

        for (i in 0 until lineItems!!.size){
            var product= itemList.lineItems?.get(i)?.name
            var quantity=itemList.lineItems?.get(i)?.quantity
            var rate=itemList.lineItems?.get(i)?.total
            var order=itemList.number

            var intent=Intent(this,OrderDetailsActivity::class.java)
            intent.putExtra(Constants.Product,product)
            intent.putExtra(Constants.Quantity,quantity)
            intent.putExtra(Constants.Price,rate)
            intent.putExtra("order",order)
            startActivity(intent)
        }



    }
}