package com.example.eatthalal.ui.main.cart.checkout.response


import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("billing")
    val billing: Billing?,
    @SerializedName("cart_hash")
    val cartHash: String?,
    @SerializedName("cart_tax")
    val cartTax: String?,
    @SerializedName("coupon_lines")
    val couponLines: List<Any?>?,
    @SerializedName("created_via")
    val createdVia: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("customer_id")
    val customerId: Int?,
    @SerializedName("customer_ip_address")
    val customerIpAddress: String?,
    @SerializedName("customer_note")
    val customerNote: String?,
    @SerializedName("customer_user_agent")
    val customerUserAgent: String?,
    @SerializedName("date_completed")
    val dateCompleted: Any?,
    @SerializedName("date_completed_gmt")
    val dateCompletedGmt: Any?,
    @SerializedName("date_created")
    val dateCreated: String?,
    @SerializedName("date_created_gmt")
    val dateCreatedGmt: String?,
    @SerializedName("date_modified")
    val dateModified: String?,
    @SerializedName("date_modified_gmt")
    val dateModifiedGmt: String?,
    @SerializedName("date_paid")
    val datePaid: String?,
    @SerializedName("date_paid_gmt")
    val datePaidGmt: String?,
    @SerializedName("discount_tax")
    val discountTax: String?,
    @SerializedName("discount_total")
    val discountTotal: String?,
    @SerializedName("fee_lines")
    val feeLines: List<Any?>?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("line_items")
    val lineItems: List<LineItem?>?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("meta_data")
    val metaData: List<MetaData?>?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("order_key")
    val orderKey: String?,
    @SerializedName("parent_id")
    val parentId: Int?,
    @SerializedName("payment_method")
    val paymentMethod: String?,
    @SerializedName("payment_method_title")
    val paymentMethodTitle: String?,
    @SerializedName("prices_include_tax")
    val pricesIncludeTax: Boolean?,
    @SerializedName("refunds")
    val refunds: List<Any?>?,
    @SerializedName("shipping")
    val shipping: Shipping?,
    @SerializedName("shipping_lines")
    val shippingLines: List<ShippingLine?>?,
    @SerializedName("shipping_tax")
    val shippingTax: String?,
    @SerializedName("shipping_total")
    val shippingTotal: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tax_lines")
    val taxLines: List<TaxLine?>?,
    @SerializedName("total")
    val total: String?,
    @SerializedName("total_tax")
    val totalTax: String?,
    @SerializedName("transaction_id")
    val transactionId: String?,
    @SerializedName("version")
    val version: String?
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

    data class LineItem(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("meta_data")
        val metaData: List<MetaData?>?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("price")
        val price: Int?,
        @SerializedName("product_id")
        val productId: Int?,
        @SerializedName("quantity")
        val quantity: Int?,
        @SerializedName("sku")
        val sku: String?,
        @SerializedName("subtotal")
        val subtotal: String?,
        @SerializedName("subtotal_tax")
        val subtotalTax: String?,
        @SerializedName("tax_class")
        val taxClass: String?,
        @SerializedName("taxes")
        val taxes: List<Taxe?>?,
        @SerializedName("total")
        val total: String?,
        @SerializedName("total_tax")
        val totalTax: String?,
        @SerializedName("variation_id")
        val variationId: Int?
    )
        data class MetaData(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("key")
            val key: String?,
            @SerializedName("value")
            val value: String?
        )

        data class Taxe(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("subtotal")
            val subtotal: String?,
            @SerializedName("total")
            val total: String?
        )


    data class Links(
        @SerializedName("collection")
        val collection: List<Collection?>?,
        @SerializedName("self")
        val self: List<Self?>?
    ) {
        data class Collection(
            @SerializedName("href")
            val href: String?
        )

        data class Self(
            @SerializedName("href")
            val href: String?
        )
    }



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

    data class ShippingLine(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("meta_data")
        val metaData: List<Any?>?,
        @SerializedName("method_id")
        val methodId: String?,
        @SerializedName("method_title")
        val methodTitle: String?,
        @SerializedName("taxes")
        val taxes: List<Any?>?,
        @SerializedName("total")
        val total: String?,
        @SerializedName("total_tax")
        val totalTax: String?
    )

    data class TaxLine(
        @SerializedName("compound")
        val compound: Boolean?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("label")
        val label: String?,
        @SerializedName("meta_data")
        val metaData: List<Any?>?,
        @SerializedName("rate_code")
        val rateCode: String?,
        @SerializedName("rate_id")
        val rateId: Int?,
        @SerializedName("shipping_tax_total")
        val shippingTaxTotal: String?,
        @SerializedName("tax_total")
        val taxTotal: String?
    )
