package com.example.eatthalal.ui.main.cart.model

import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem

data class CartItemModel(var product: NewProductResponseItem, var quantity:Int=0)