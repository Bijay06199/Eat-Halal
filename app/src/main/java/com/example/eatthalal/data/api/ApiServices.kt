package com.example.eatthalal.data.api

import com.example.eatthalal.ui.auth.login.response.CustomerOrderResponse
import com.example.eatthalal.ui.auth.login.response.CustomerResponse
import com.example.eatthalal.ui.auth.register.body.RegisterBody
import com.example.eatthalal.ui.auth.register.response.RegisterResponse
import com.example.eatthalal.ui.main.cart.checkout.body.OrderBody
import com.example.eatthalal.ui.main.cart.checkout.response.OrderResponse
import com.example.eatthalal.ui.main.categories.response.CategoriesResponse
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import retrofit2.Response
import retrofit2.http.*

interface zorbiApiServices{

    var categoryId:Int


    @GET("/wp-json/wc/v3/products?category=23")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun getTodaysSpecial():Response<List<NewProductResponseItem>>


    @GET("/wp-json/wc/v3/products/categories?per_page=50")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun getCategories(): Response<List<CategoriesResponse>>

    @GET("wp-json/wc/v3/products?per_page=100")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun getProduct():Response<List<NewProductResponseItem>>

    @POST("/wp-json/wc/v3/customers")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun register(
        @Body params:RegisterBody
    ):Response<RegisterResponse>

    @POST("/wp-json/wc/v3/orders")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun order(
        @Body params: OrderBody
    ):Response<OrderResponse>

    @GET("/wp-json/wc/v3/products?category=21")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun getFeaturedProduct():Response<List<NewProductResponseItem>>

    @GET("/wp-json/wc/v3/products?orderby=date")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
     suspend fun getLatestProducts():Response<List<NewProductResponseItem>>

    @GET("/wp-json/wc/v3/products?category=19")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun getOnSaleProducts():Response<List<NewProductResponseItem>>

    @GET("/wp-json/wc/v3/products?category=24")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
     suspend fun getBanner():Response<List<NewProductResponseItem>>

    @GET("/wp-json/wc/v3/products")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
     suspend fun getProductCategoryWise(
        @Query ("category")category:Int?
    ):Response<List<NewProductResponseItem>>

    @GET("wp-json/wc/v3/customers")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun getCustomerDetails(
        @Query("email")email:String?
    ):Response<List<CustomerResponse>>

    @GET("/wp-json/wc/v2/orders")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun getCustomersOrder(
        @Query("customer")customer:Int?
    ):Response<List<CustomerOrderResponse>>


    @GET("wp-json/wc/v3/products")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun getProductPage(
        @Query("page")page:Int?,
        @Query("per_page")per_page:Int?
    ):Response<List<NewProductResponseItem>>


    @GET("wp-json/wc/v3/customers")
    @Headers("Authorization:Basic IGNrXzgxYWU5NmQ0MmQ3MWY3NDRkMzNjZjg0YmYwOGFlNTg3MDJlYmYxMjY6Y3NfYWRlMmU4N2EzM2I0MTk3MzgyMGQzNDA1NDNkYTFhZGY0MDUxODA1Ng")
    suspend fun getCustomer():Response<List<CustomerResponse>>


}