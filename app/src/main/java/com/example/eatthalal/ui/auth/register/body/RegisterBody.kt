package com.example.eatthalal.ui.auth.register.body


import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("billing")
    val billing: Billing?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("shipping")
    val shipping: Shipping?,
    @SerializedName("username")
    val username: String?
)
    data class Billing(
        @SerializedName("address_1")
        val address1: String?,
        @SerializedName("address_2")
        val address2: String?,
        @SerializedName("city")
        val city: String?,
        @SerializedName("company")
        val company: String?,
        @SerializedName("country")
        val country: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("first_name")
        val firstName: String?,
        @SerializedName("last_name")
        val lastName: String?,
        @SerializedName("phone")
        val phone: String?,
        @SerializedName("postcode")
        val postcode: String?,
        @SerializedName("state")
        val state: String?
    )

    data class Shipping(
        @SerializedName("address_1")
        val address1: String?,
        @SerializedName("address_2")
        val address2: String?,
        @SerializedName("city")
        val city: String?,
        @SerializedName("company")
        val company: String?,
        @SerializedName("country")
        val country: String?,
        @SerializedName("first_name")
        val firstName: String?,
        @SerializedName("last_name")
        val lastName: String?,
        @SerializedName("postcode")
        val postcode: String?,
        @SerializedName("state")
        val state: String?
    )
