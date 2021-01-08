package com.example.eatthalal.ui.main.home.offers

import androidx.lifecycle.viewModelScope
import com.example.eatthalal.base.BaseViewModel
import com.example.eatthalal.data.repo.FeaturedProductRepository
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import com.example.eatthalal.utils.ApiException
import com.example.eatthalal.utils.NoInternetException
import com.example.eatthalal.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class OffersViewModel(private var featuredProductRepository: FeaturedProductRepository) :BaseViewModel(){

    var featuredProductEvent = SingleLiveEvent<Unit>()
    var productName: List<NewProductResponseItem>? = null


    fun getFeaturedProduct() {

        viewModelScope.launch {
            try {

                val featuredProduct = featuredProductRepository.getProduct()
                productName = featuredProduct.body()
                featuredProductEvent.call()

            }  catch (e: NoInternetException) {
            }catch (e: ApiException){

            }
        }
    }
}