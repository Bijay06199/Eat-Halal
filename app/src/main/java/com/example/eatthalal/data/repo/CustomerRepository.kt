package com.example.eatthalal.data.repo

import com.example.eatthalal.data.api.zorbiApiServices
import com.example.eatthalal.ui.auth.login.response.CustomerResponse
import retrofit2.Response

class CustomerRepository (var apiServices: zorbiApiServices){
    suspend fun getCustomer(): Response<List<CustomerResponse>> {
        return apiServices.getCustomer()
    }
}