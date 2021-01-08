package com.example.eatthalal.ui.auth.login.orders

import androidx.lifecycle.viewModelScope
import com.example.eatthalal.base.BaseViewModel
import com.example.eatthalal.data.repo.CustomerOrderRepository
import com.example.eatthalal.ui.auth.login.response.CustomerOrderResponse
import com.example.eatthalal.utils.ApiException
import com.example.eatthalal.utils.NoInternetException
import com.example.eatthalal.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CustomerOrderViewModel(var customerOrderRepository: CustomerOrderRepository) :BaseViewModel(){

    var orderEvent=SingleLiveEvent<Unit>()
    var orders:List<CustomerOrderResponse>?=null

    fun getOrders(id:Int?){

        viewModelScope.launch {
            try {
                val response = customerOrderRepository.getCustomerOrder(id)
                orders = response.body()!!
                orderEvent.call()
            } catch (e: NoInternetException){

            }catch (e: ApiException){

            }
        }


    }


    }


