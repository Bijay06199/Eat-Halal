package com.example.eatthalal.ui.main.cart.checkout.body


import com.google.gson.annotations.SerializedName

data class OrderBody(
    @SerializedName("customer_id")
    var customer_id:Int,
    @SerializedName("billing")
    val billing: Billing?,
    @SerializedName("line_items")
    val lineItems: ArrayList<LineItem>?,
    @SerializedName("payment_method")
    val paymentMethod: String?,
    @SerializedName("payment_method_title")
    val paymentMethodTitle: String?,
    @SerializedName("set_paid")
    val setPaid: Boolean?,
    @SerializedName("shipping")
    val shipping: Shipping?,
    @SerializedName("shipping_lines")
    val shippingLines: List<ShippingLine?>?
)
    data class Billing(
        @SerializedName("address_1")
        val address1: String?,
        @SerializedName("address_2")
        val address2: String?,
        @SerializedName("city")
        val city: String?,
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

    data class LineItem(
        @SerializedName("product_id")
        val productId: Int?,
        @SerializedName("quantity")
        val quantity: Int?,
        @SerializedName("variation_id")
        val variationId: Int?
    )

    data class Shipping(
        @SerializedName("address_1")
        val address1: String?,
        @SerializedName("address_2")
        val address2: String?,
        @SerializedName("city")
        val city: String?,
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

    data class ShippingLine(
        @SerializedName("method_id")
        val methodId: String?,
        @SerializedName("method_title")
        val methodTitle: String?,
        @SerializedName("total")
        val total: String?
    )
