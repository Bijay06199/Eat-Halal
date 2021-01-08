package com.example.eatthalal.data.repo

import com.example.eatthalal.data.api.zorbiApiServices
import com.example.eatthalal.data.network.SafeApiRequest
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import retrofit2.Response

class BannerRepository (var apiServices: zorbiApiServices):SafeApiRequest(){
    suspend fun getBanner(): Response<List<NewProductResponseItem>> {
        return apiServices.getBanner()
    }
}