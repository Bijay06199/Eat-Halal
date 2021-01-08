package com.example.eatthalal.ui.main.cart

import android.content.Context
import com.example.eatthalal.ui.main.cart.model.CartItemModel
import io.paperdb.Paper

class ShoppingCart {

    companion object{

        fun addItem(cartItem:CartItemModel,context: Context){
              val cart=ShoppingCart.getCart()

            val targetItem=cart.singleOrNull{it.product.id==cartItem.product.id}

           if (targetItem==null){
               cartItem.quantity++

               cart.add(cartItem)
           }else{
               targetItem.quantity++
           }
            ShoppingCart.saveCart(cart)

        }

        fun removeItem(cartItem: CartItemModel, context: Context) {

            val cart = ShoppingCart.getCart()


            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }

            if (targetItem != null) {

                if (targetItem.quantity > 1) {

                    targetItem.quantity--
                }
                else if (targetItem.quantity==1){
                    cart.remove(targetItem)
                }

            }

            ShoppingCart.saveCart(cart)

        }

        fun getCart(): MutableList<CartItemModel> {
            return Paper.book().read("cart", mutableListOf())
        }

        fun saveCart(cart: MutableList<CartItemModel>) {
            Paper.book().write("cart", cart)
        }

        fun getShoppingCartSize(): Int {


            var cartSize = 0
            ShoppingCart.getCart().forEach {
                cartSize += it.quantity;
            }

            return cartSize
        }

        fun clearCart() {
           return Paper.book().delete("cart")
        }
    }
}