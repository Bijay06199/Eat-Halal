package com.example.eatthalal.ui.main.categories.categoriesDetail

import androidx.lifecycle.viewModelScope
import com.example.eatthalal.base.BaseViewModel
import com.example.eatthalal.data.repo.CategoriesWiseRepository
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import com.example.eatthalal.utils.ApiException
import com.example.eatthalal.utils.NoInternetException
import com.example.eatthalal.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CategoriesItemViewModel( val categoriesWiseRepository: CategoriesWiseRepository) :BaseViewModel(){

    var categoryWise:List<NewProductResponseItem>?=null
    var categoryWiseEvent=SingleLiveEvent<Unit>()


    fun getCategoryWise( id:Int?) {

        viewModelScope.launch {
            try {
                val response = categoriesWiseRepository.getCategoriesWise(id)
                categoryWise = response.body()!!
                categoryWiseEvent.call()
            } catch (e: NoInternetException){

            }catch (e: ApiException){

            }catch (e:java.lang.NullPointerException){}
        }
    }

}