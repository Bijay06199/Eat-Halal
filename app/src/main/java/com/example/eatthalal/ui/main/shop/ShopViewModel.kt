package com.example.eatthalal.ui.main.shop

import androidx.lifecycle.viewModelScope
import com.example.eatthalal.base.BaseViewModel
import com.example.eatthalal.data.repo.ProductRepository
import com.example.eatthalal.data.repo.ShoppingRepository
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import com.example.eatthalal.utils.ApiException
import com.example.eatthalal.utils.NoInternetException
import com.example.eatthalal.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class ShopViewModel(private val productRepository: ProductRepository,private val shoppingRepository: ShoppingRepository) :BaseViewModel(){

    var productEvent= SingleLiveEvent<Unit>()
    var productName: List<NewProductResponseItem>?=null


    fun getProduct(page:Int,per_page:Int){

        viewModelScope.launch {
            try{
                val response=shoppingRepository.getProduct(page,per_page)
                productName=response.body()
                productEvent.call()
            }
            catch (e: NoInternetException) {
            }catch (e: ApiException){

            }catch (e:java.lang.NullPointerException){}
        }
    }

}