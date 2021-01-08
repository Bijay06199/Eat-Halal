package com.example.eatthalal.ui.auth.register.response


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("billing")
    val billing: Billing?,
    @SerializedName("date_created")
    val dateCreated: String?,
    @SerializedName("date_created_gmt")
    val dateCreatedGmt: String?,
    @SerializedName("date_modified")
    val dateModified: String?,
    @SerializedName("date_modified_gmt")
    val dateModifiedGmt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_paying_customer")
    val isPayingCustomer: Boolean?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("meta_data")
    val metaData: List<MetaData?>?,
    @SerializedName("role")
    val role: String?,
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

    data class Links(
        @SerializedName("collection")
        val collection: List<Collection?>?,
        @SerializedName("self")
        val self: List<Self?>?
    )
        data class Collection(
            @SerializedName("href")
            val href: String?
        )

        data class Self(
            @SerializedName("href")
            val href: String?
        )


    data class MetaData(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("key")
        val key: String?,
        @SerializedName("value")
        val value: String?
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
