package com.example.eatthalal.data.repo

import com.example.eatthalal.data.api.zorbiApiServices
import com.example.eatthalal.ui.auth.login.response.CustomerOrderResponse
import retrofit2.Response

class CustomerOrderRepository (var apiServices: zorbiApiServices){
    suspend fun getCustomerOrder(id: Int?): Response<List<CustomerOrderResponse>> {
        return apiServices.getCustomersOrder(id)
    }
}