package com.example.eatthalal.ui.main.home.latestProducts

import androidx.lifecycle.viewModelScope
import com.example.eatthalal.base.BaseViewModel
import com.example.eatthalal.data.repo.LatestProductRepository
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import com.example.eatthalal.utils.ApiException
import com.example.eatthalal.utils.NoInternetException
import com.example.eatthalal.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class LatestProductViewModel(var latestProductRepository: LatestProductRepository) :BaseViewModel(){

    var latestProduct:List<NewProductResponseItem>?=null
    var latestProductEvent= SingleLiveEvent<Unit>()


    fun getLatestProduct() {
        viewModelScope.launch {
            try {

                val latest = latestProductRepository.getProduct()
                latestProduct = latest.body()
                latestProductEvent.call()

            } catch (e: NoInternetException) {
            }catch (e: ApiException){

            }
        }

    }
}