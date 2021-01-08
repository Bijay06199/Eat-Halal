package com.example.eatthalal.data.repo

import com.example.eatthalal.data.api.zorbiApiServices
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import retrofit2.Response

class CategoriesWiseRepository (var apiServices: zorbiApiServices){
    suspend fun getCategoriesWise(id: Int?): Response<List<NewProductResponseItem>> {
        return apiServices.getProductCategoryWise(id)
    }
}