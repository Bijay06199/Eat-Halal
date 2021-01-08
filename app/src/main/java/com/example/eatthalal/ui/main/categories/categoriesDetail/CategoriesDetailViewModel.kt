package com.example.eatthalal.ui.main.categories.categoriesDetail

import androidx.lifecycle.viewModelScope
import com.example.eatthalal.base.BaseViewModel
import com.example.eatthalal.data.repo.CategoriesRepository
import com.example.eatthalal.ui.main.categories.response.CategoriesResponse
import com.example.eatthalal.utils.ApiException
import com.example.eatthalal.utils.NoInternetException
import com.example.eatthalal.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CategoriesDetailViewModel(private val getCategoriesRepository: CategoriesRepository) :BaseViewModel(){

    var categoriesEvent= SingleLiveEvent<Unit>()
    var categoryName:List<CategoriesResponse>?=null


    fun categories(){
        viewModelScope.launch {
            try {
                val response= getCategoriesRepository.getCategories()
                categoryName=response.body()
                categoriesEvent.call()
            }
            catch (e: NoInternetException){

            }catch (e: ApiException){

            }catch (e:java.lang.NullPointerException){}
        }
    }
}