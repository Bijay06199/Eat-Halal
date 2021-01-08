package com.example.eatthalal.ui.main.shop.response


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products")
    val products: List<Product?>?
)
    data class Product(
        @SerializedName("attributes")
        val attributes: List<Any?>?,
        @SerializedName("average_rating")
        val averageRating: String?,
        @SerializedName("backordered")
        val backordered: Boolean?,
        @SerializedName("backorders_allowed")
        val backordersAllowed: Boolean?,
        @SerializedName("button_text")
        val buttonText: String?,
        @SerializedName("catalog_visibility")
        val catalogVisibility: String?,
        @SerializedName("categories")
        val categories: List<String?>?,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("cross_sell_ids")
        val crossSellIds: List<Any?>?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("dimensions")
        val dimensions: Dimensions?,
        @SerializedName("download_expiry")
        val downloadExpiry: Int?,
        @SerializedName("download_limit")
        val downloadLimit: Int?,
        @SerializedName("download_type")
        val downloadType: String?,
        @SerializedName("downloadable")
        val downloadable: Boolean?,
        @SerializedName("downloads")
        val downloads: List<Any?>?,
        @SerializedName("featured")
        val featured: Boolean?,
        @SerializedName("featured_src")
        val featuredSrc: Any?,
        @SerializedName("grouped_products")
        val groupedProducts: List<Any?>?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("images")
        val images: List<Image?>?,
        @SerializedName("in_stock")
        val inStock: Boolean?,
        @SerializedName("managing_stock")
        val managingStock: Boolean?,
        @SerializedName("menu_order")
        val menuOrder: Int?,
        @SerializedName("on_sale")
        val onSale: Boolean?,
        @SerializedName("parent")
        val parent: List<Any?>?,
        @SerializedName("parent_id")
        val parentId: Int?,
        @SerializedName("permalink")
        val permalink: String?,
        @SerializedName("price")
        val price: String?,
        @SerializedName("price_html")
        val priceHtml: String?,
        @SerializedName("product_url")
        val productUrl: String?,
        @SerializedName("purchase_note")
        val purchaseNote: String?,
        @SerializedName("purchaseable")
        val purchaseable: Boolean?,
        @SerializedName("rating_count")
        val ratingCount: Int?,
        @SerializedName("regular_price")
        val regularPrice: String?,
        @SerializedName("related_ids")
        val relatedIds: List<Int?>?,
        @SerializedName("reviews_allowed")
        val reviewsAllowed: Boolean?,
        @SerializedName("sale_price")
        val salePrice: Any?,
        @SerializedName("shipping_class")
        val shippingClass: String?,
        @SerializedName("shipping_class_id")
        val shippingClassId: Any?,
        @SerializedName("shipping_required")
        val shippingRequired: Boolean?,
        @SerializedName("shipping_taxable")
        val shippingTaxable: Boolean?,
        @SerializedName("short_description")
        val shortDescription: String?,
        @SerializedName("sku")
        val sku: String?,
        @SerializedName("sold_individually")
        val soldIndividually: Boolean?,
        @SerializedName("status")
        val status: String?,
        @SerializedName("stock_quantity")
        val stockQuantity: Any?,
        @SerializedName("tags")
        val tags: List<Any?>?,
        @SerializedName("tax_class")
        val taxClass: String?,
        @SerializedName("tax_status")
        val taxStatus: String?,
        @SerializedName("taxable")
        val taxable: Boolean?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("total_sales")
        val totalSales: Int?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("updated_at")
        val updatedAt: String?,
        @SerializedName("upsell_ids")
        val upsellIds: List<Any?>?,
        @SerializedName("variations")
        val variations: List<Any?>?,
        @SerializedName("virtual")
        val virtual: Boolean?,
        @SerializedName("visible")
        val visible: Boolean?,
        @SerializedName("weight")
        val weight: Any?
    ) {
        data class Dimensions(
            @SerializedName("height")
            val height: String?,
            @SerializedName("length")
            val length: String?,
            @SerializedName("unit")
            val unit: String?,
            @SerializedName("width")
            val width: String?
        )

        data class Image(
            @SerializedName("alt")
            val alt: String?,
            @SerializedName("created_at")
            val createdAt: String?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("position")
            val position: Int?,
            @SerializedName("src")
            val src: String?,
            @SerializedName("title")
            val title: String?,
            @SerializedName("updated_at")
            val updatedAt: String?
        )
    }
