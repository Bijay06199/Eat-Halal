package com.example.eatthalal.ui.auth.register

import android.content.Intent
import android.view.View
import com.example.eatthalal.base.BaseViewModel
import com.example.eatthalal.data.repo.RegisterRepository
import com.example.eatthalal.utils.AuthListenerInfo
import com.example.eatthalal.utils.SingleLiveEvent

class RegisterNameViewModel(private val registerRepository: RegisterRepository) :BaseViewModel(){
   var  backClickedEvent=SingleLiveEvent<Unit>()
    var firstName:String?=null
    var lastName:String?=null
    var authListenerInfo:AuthListenerInfo?=null

    fun back(){
        backClickedEvent.call()
    }

    fun next(view: View){
        if(firstName.isNullOrEmpty()){
            authListenerInfo?.onInfo("Enter first Name")
        }
        else if(lastName.isNullOrEmpty()){
            authListenerInfo?.onInfo("Enter last Name")
        }
        else{

            registerRepository.preferenceManager.setFirstName(firstName)
            registerRepository.preferenceManager.setLastName(lastName)

           Intent(view.context,RegisterPasswordActivity::class.java).also {
               view.context.startActivity(it)

           }
        }
    }
}