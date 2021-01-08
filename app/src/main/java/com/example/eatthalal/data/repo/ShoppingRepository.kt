package com.example.eatthalal.data.repo

import com.example.eatthalal.data.api.zorbiApiServices
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import retrofit2.Response

class ShoppingRepository (var apiServices: zorbiApiServices){
    suspend fun getProduct(page:Int,per_page:Int): Response<List<NewProductResponseItem>> {
        return apiServices.getProductPage(page,per_page)
    }
}