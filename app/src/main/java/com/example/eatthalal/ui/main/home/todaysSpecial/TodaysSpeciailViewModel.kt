package com.example.eatthalal.ui.main.home.todaysSpecial

import androidx.lifecycle.viewModelScope
import com.example.eatthalal.base.BaseViewModel
import com.example.eatthalal.data.repo.TodaysSpecialRepository
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import com.example.eatthalal.utils.ApiException
import com.example.eatthalal.utils.AuthListenerInfo
import com.example.eatthalal.utils.NoInternetException
import com.example.eatthalal.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class TodaysSpeciailViewModel(val todaysSpecialRepository: TodaysSpecialRepository) :BaseViewModel(){

    var todaysSpecialEvent= SingleLiveEvent<Unit>()
    var todaysSpecial:List<NewProductResponseItem>?=null
    var authListenerInfo: AuthListenerInfo? = null

    fun getTodaysSpecial() {
        viewModelScope.launch {
            try {

                val latest = todaysSpecialRepository.getProduct()
                todaysSpecial = latest.body()
                todaysSpecialEvent.call()

            } catch (e: NoInternetException) {
                authListenerInfo?.onWarning(e.message!!)
            }catch (e: ApiException){

            }catch (e:java.lang.NullPointerException){}
        }

    }


}