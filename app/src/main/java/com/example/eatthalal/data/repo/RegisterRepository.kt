package com.example.eatthalal.data.repo

import com.example.eatthalal.data.api.zorbiApiServices
import com.example.eatthalal.data.network.SafeApiRequest
import com.example.eatthalal.data.prefs.PreferenceManager
import com.example.eatthalal.ui.auth.register.body.RegisterBody
import com.example.eatthalal.ui.auth.register.body.Shipping
import com.example.eatthalal.ui.auth.register.response.RegisterResponse
import retrofit2.Response

class RegisterRepository (private val zorbiApiServices: zorbiApiServices,val preferenceManager: PreferenceManager):SafeApiRequest(){

    suspend fun register(
        email:String,
        userName:String,
         address:String
    ):Response<RegisterResponse>{

        val firstName=preferenceManager.getFirstName()
        val lastName=preferenceManager.getLastName()
        val userPassword=preferenceManager.getPassword()


        val requestData=RegisterBody(null,email,firstName,lastName, Shipping(address,null,null,null,null,null,null,null,null),userName)
        return zorbiApiServices.register(requestData)
    }
}