package com.example.eatthalal.ui.main.home.eatHalal

import androidx.lifecycle.viewModelScope
import com.example.eatthalal.base.BaseViewModel
import com.example.eatthalal.data.repo.OnSaleRepository
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import com.example.eatthalal.utils.ApiException
import com.example.eatthalal.utils.NoInternetException
import com.example.eatthalal.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class EatHalalViewModel(private var onSaleRepository: OnSaleRepository) :BaseViewModel(){

    var onSale:List<NewProductResponseItem>?=null
    var onSaleProductEvent= SingleLiveEvent<Unit>()


    fun getOnSaleProduct(){
        viewModelScope.launch {
            try {

                val onsaleProduct = onSaleRepository.getProduct()
                onSale = onsaleProduct.body()
                onSaleProductEvent.call()

            } catch (e: NoInternetException) {
            }catch (e: ApiException){

            }
        }


    }
}