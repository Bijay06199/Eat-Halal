package com.example.eatthalal.data.repo

import com.example.eatthalal.data.api.zorbiApiServices
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import retrofit2.Response

class ProductRepository (var apiServices: zorbiApiServices){
    suspend fun getProduct():Response<List<NewProductResponseItem>>{
        return apiServices.getProduct()
    }
}