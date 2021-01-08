package com.example.eatthalal.data.repo

import com.example.eatthalal.data.api.zorbiApiServices
import com.example.eatthalal.ui.main.categories.response.CategoriesResponse
import retrofit2.Response

class CategoriesRepository (var apiServices: zorbiApiServices){
    suspend fun getCategories(): Response<List<CategoriesResponse>> {
        return apiServices.getCategories()
    }
}