package com.example.eatthalal.data.repo

import com.example.eatthalal.data.api.zorbiApiServices
import com.example.eatthalal.data.network.SafeApiRequest
import com.example.eatthalal.data.prefs.PreferenceManager
import com.example.eatthalal.ui.main.cart.ShoppingCart
import com.example.eatthalal.ui.main.cart.checkout.body.Billing
import com.example.eatthalal.ui.main.cart.checkout.body.LineItem
import com.example.eatthalal.ui.main.cart.checkout.body.OrderBody
import com.example.eatthalal.ui.main.cart.checkout.body.Shipping
import com.example.eatthalal.ui.main.cart.checkout.response.OrderResponse
import com.example.eatthalal.ui.main.cart.model.CartItemModel
import org.json.JSONException
import retrofit2.Response

class OrderRepository (private val apiServices: zorbiApiServices,val preferenceManager: PreferenceManager):SafeApiRequest(){
    suspend fun order(
        firstName:String,
        lastName:String,
        contactNumber:String,
        email:String,
        address1:String,
        address2:String
    ):Response<OrderResponse>{
        val requestData = OrderBody(
            preferenceManager.getCustomerId()!!,
            Billing(
                address1,
                address2,
                null,
                "",
                email,
                firstName,
                lastName,
                contactNumber,
                "",
                ""
            ),
           getCartItemsFromDB(),

            "COD",
            "Cash on Delivery"
            ,
            null,
            (Shipping(address1, address2, "", null, firstName, lastName, null, null)),
            null
        )

        return apiServices.order(requestData)
    }


    private fun getCartItemsFromDB(): ArrayList<LineItem>? {

        val cartDTOList: List<CartItemModel> = ShoppingCart.getCart()
        val jsonArray = ArrayList<LineItem>()
        if (cartDTOList != null && cartDTOList.size > 0) {
            for (i in cartDTOList.indices) {
                try {
                    jsonArray.add( LineItem(cartDTOList[i].product.id!!,cartDTOList[i].quantity,0))

                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }
        return jsonArray
    }
}