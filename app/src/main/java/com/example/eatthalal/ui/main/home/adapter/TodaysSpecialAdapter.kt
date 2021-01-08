package com.example.eatthalal.ui.main.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eatthalal.R
import com.example.eatthalal.ui.main.MainActivity
import com.example.eatthalal.ui.main.cart.ShoppingCart
import com.example.eatthalal.ui.main.cart.model.CartItemModel
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.product_items_recyclerview.view.*


class TodaysSpecialAdapter(private val context: Context, private val listener:OnItemClickListener, var cartItems:ArrayList<NewProductResponseItem>): RecyclerView.Adapter<TodaysSpecialAdapter.ViewHolder>(){


    var count: Int = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodaysSpecialAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_items_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cartItems.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var images=cartItems[position].images
        var name=cartItems[position].name

        if (!name.equals("Banner Test 1", ignoreCase = true) && !name.equals(
                "Banner Test 3", ignoreCase = true) && !name.equals("Slider Banner 2",ignoreCase = true)
        ){


            for(i in 0 until images!!.size){
                var imageUrl = images[i]?.src


                Glide.with(holder.productImage)
                    .load(imageUrl)
                    .placeholder(R.drawable.place_holder_icon)
                    .into(holder.productImage)
            }

            holder.productName.text = cartItems[position].name
            holder.productPrice.text = cartItems[position].price




            holder.bindProduct(cartItems[position])
        } else {
            holder.root.visibility = View.GONE
            holder.root.layoutParams = RecyclerView.LayoutParams(0, 0)
            holder.root.removeView(holder.root)



        }



        holder.root.setOnClickListener {
            listener.onItemLatestSelect(holder.adapterPosition, cartItems[holder.adapterPosition])
        }



    }


    inner class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {
        val productName = containerView.tv_product_name
        val productImage = containerView.iv_item
        val productPrice = containerView.tv_price
        val root = containerView.root




        @SuppressLint("CheckResult")
        fun bindProduct(cartItem: NewProductResponseItem) {


            Observable.create(ObservableOnSubscribe<MutableList<CartItemModel>> {


                var cartItemModel= CartItemModel(cartItem,0)

                var _counter=cartItemModel.quantity


                itemView.layout_add.setOnClickListener{view->

                    _counter++
                    listener.onLayoutAddLatestClick(adapterPosition, cartItems[adapterPosition])

                    val item= CartItemModel(cartItem)
                    ShoppingCart.addItem(item,itemView.context)


                    itemView.layout_add.visibility = View.GONE
                    itemView.cl_add_main.visibility = View.GONE
                    itemView.add_subtract_button.visibility = View.VISIBLE
                    ShoppingCart.getCart()
                    (itemView.context as MainActivity).badge.number=ShoppingCart.getCart().size
                    itemView.tv_cart_quantity.setText(Integer.toString(_counter))





                }

                itemView.cl_add_main.setOnClickListener {view ->

                    _counter++
                    val item= CartItemModel(cartItem)
                    ShoppingCart.addItem(item,itemView.context)


                    itemView.layout_add.visibility = View.GONE
                    itemView.cl_add_main.visibility = View.GONE
                    itemView.add_subtract_button.visibility = View.VISIBLE
                    ShoppingCart.getCart()
                    (itemView.context as MainActivity).badge.number=ShoppingCart.getCart().size
                    itemView.tv_cart_quantity.setText(Integer.toString(_counter))

                }

                itemView.cl_add.setOnClickListener {view->

                    _counter++
                    val item= CartItemModel(cartItem)
                    ShoppingCart.addItem(item,itemView.context)


                    itemView.layout_add.visibility = View.GONE
                    itemView.cl_add_main.visibility = View.GONE
                    itemView.add_subtract_button.visibility = View.VISIBLE
                    ShoppingCart.getCart()
                    (itemView.context as MainActivity).badge.number=ShoppingCart.getCart().size
                    itemView.tv_cart_quantity.setText(Integer.toString(_counter))




                }

                itemView.cl_subtract.setOnClickListener {

                    _counter--
                    val item= CartItemModel(cartItem)
                    ShoppingCart.removeItem(item,itemView.context)
                    ShoppingCart.getCart()
                    (itemView.context as MainActivity).badge.number=ShoppingCart.getCart().size
                    itemView.tv_cart_quantity.setText(Integer.toString(_counter))

                    if (_counter==0){

                        ShoppingCart.removeItem(item,itemView.context)
                        itemView.layout_add.visibility = View.VISIBLE
                        itemView.cl_add_main.visibility = View.VISIBLE
                        (itemView.context as MainActivity).badge.number=ShoppingCart.getCart().size
                        itemView.add_subtract_button.visibility = View.GONE

                    }




                }





            }).subscribe{
                    cartItem->
                var quantity=0
                var productId=0

                cartItem.forEach { cartItem->
                    quantity += cartItem.quantity

                }


                itemView.tv_cart_quantity.setText(quantity)

                notifyDataSetChanged()

            }


        }

    }

    interface OnItemClickListener {
        fun onItemLatestSelect(position: Int, itemList: NewProductResponseItem)
        fun onLayoutAddLatestClick(position: Int, itemList: NewProductResponseItem)
//        fun onAddLatestClick(position: Int, itemList: NewProductResponseItem)
//        fun onSubtractLatestClick(position: Int, itemList: NewProductResponseItem)
    }

    fun filteredList(filteredNames:ArrayList<NewProductResponseItem>){

        this.cartItems=filteredNames
        notifyDataSetChanged()

    }

    }

