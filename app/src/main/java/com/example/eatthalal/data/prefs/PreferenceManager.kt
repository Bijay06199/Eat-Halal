package com.example.eatthalal.data.prefs

interface PreferenceManager {
    fun setToken(token:String)
    fun getToken():String
    fun setIsLoggedIn(isLoggedIn: Boolean)
    fun getIsLoggedIn():Boolean
    fun getFirstName():String
    fun getLastName():String
    fun getPassword():String
    fun setFirstName(firstName:String?)
    fun setLastName(lastName:String?)
    fun setPassword(password:String?)
    fun setCustomerId(customerId:Int?)
    fun getCustomerId():Int?
    fun setEmail(email:String?)
    fun getEmail():String?
    fun setNumber(number:String?)
    fun getNumber():String?
    fun getAddress():String?
    fun setAddress(address:String?)
    fun setProuductId(productId:Int)
    fun getProductId():Int
    fun seQuantity(quantity:Int)
    fun getQuantity():Int


}