package com.example.eatthalal.ui.main.shop.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eatthalal.R
import com.example.eatthalal.ui.main.cart.ShoppingCart
import com.example.eatthalal.ui.main.cart.model.CartItemModel
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.product_items_recyclerview.view.*

class ProductAdapter (private val listener:OnItemClickListener,var itemList:List<NewProductResponseItem>):RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var count: Int = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_items_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var images=itemList[position].images
        var name=itemList[position].name

        if (!name.equals("Banner Test 1", ignoreCase = true) && !name.equals(
                "Banner Test 3", ignoreCase = true) && !name.equals("Slider Banner 2",ignoreCase = true)&& !name.equals("Banner Test 4",ignoreCase = true)&& !name.equals("Banner Test 2",ignoreCase = true)
        ){


            for(i in 0 until images!!.size){
                var imageUrl = images[i]?.src


                Glide.with(holder.productImage)
                    .load(imageUrl)
                    .placeholder(R.drawable.place_holder_icon)
                    .into(holder.productImage)
            }

            holder.productName.text = itemList[position].name
            holder.productPrice.text = itemList[position].price

            var salePrice=itemList[position].salePrice
            var regularPrice=itemList[position].regularPrice

            if (salePrice==""){
                holder.regularPrice.setText("")
            }
            else {
                holder.regularPrice.setText(regularPrice)
                holder.regularPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
            }


            holder.bindProduct(itemList[position])
        } else {
            holder.root.visibility = View.GONE
            holder.root.layoutParams = RecyclerView.LayoutParams(0, 0)
            holder.root.removeView(holder.root)



        }

        holder.bindProduct(itemList[position])
        holder.productName.text = itemList[position].name
        holder.productPrice.text = itemList[position].price


        holder.root.setOnClickListener {
            listener.onItemSelect(holder.adapterPosition, itemList[holder.adapterPosition])
            listener.onLayoutAddClick(holder.adapterPosition, itemList[holder.adapterPosition])
            listener.onAddClick(holder.adapterPosition, itemList[holder.adapterPosition])
            listener.onSubtractClick(holder.adapterPosition, itemList[holder.adapterPosition])

        }
    }


    inner class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {
        val productName = containerView.tv_product_name
        val productImage = containerView.iv_item
        val productPrice = containerView.tv_price
        val root = containerView.root
        val add = containerView.cl_add
        val regularPrice=containerView.tv_regular_price




        @SuppressLint("CheckResult")
        fun bindProduct(product: NewProductResponseItem) {


            Observable.create(ObservableOnSubscribe<MutableList<CartItemModel>> {

                var cartItemModel=CartItemModel(product,0)

                var _counter=cartItemModel.quantity


                itemView.layout_add.setOnClickListener{view->

                    _counter++
                    listener.onLayoutAddClick(adapterPosition, itemList[adapterPosition])



                    val item= CartItemModel(product)
                    ShoppingCart.addItem(item,itemView.context)


                    itemView.layout_add.visibility = View.GONE
                    itemView.cl_add_main.visibility = View.GONE
                    itemView.add_subtract_button.visibility = View.VISIBLE
                    ShoppingCart.getCart()
                    itemView.tv_cart_quantity.setText(Integer.toString(_counter))





                }

                itemView.cl_add_main.setOnClickListener {view ->

                    _counter++
                    val item= CartItemModel(product)
                    ShoppingCart.addItem(item,itemView.context)


                    itemView.layout_add.visibility = View.GONE
                    itemView.cl_add_main.visibility = View.GONE
                    itemView.add_subtract_button.visibility = View.VISIBLE
                    ShoppingCart.getCart()
                    itemView.tv_cart_quantity.setText(Integer.toString(_counter))

                }

                itemView.cl_add.setOnClickListener {view->

                    _counter++
                    val item= CartItemModel(product)
                    ShoppingCart.addItem(item,itemView.context)


                    itemView.layout_add.visibility = View.GONE
                    itemView.cl_add_main.visibility = View.GONE
                    itemView.add_subtract_button.visibility = View.VISIBLE
                    ShoppingCart.getCart()
                    itemView.tv_cart_quantity.setText(Integer.toString(_counter))




                }

                itemView.cl_subtract.setOnClickListener {

                    _counter--
                    val item= CartItemModel(product)
                    ShoppingCart.removeItem(item,itemView.context)
                    ShoppingCart.getCart()
                    itemView.tv_cart_quantity.setText(Integer.toString(_counter))

                    if (_counter==0){
                        ShoppingCart.removeItem(item,itemView.context)
                        itemView.layout_add.visibility = View.VISIBLE
                        itemView.cl_add_main.visibility = View.VISIBLE
                        itemView.add_subtract_button.visibility = View.GONE

                    }




                }


            }).subscribe { cartItem ->
                var quantity = 0

                cartItem.forEach { cartItem ->
                    quantity += cartItem.quantity
                }


                itemView.tv_cart_quantity.setText(quantity)

                notifyDataSetChanged()

            }
        }

    }

    interface OnItemClickListener {
        fun onItemSelect(position: Int, itemList: NewProductResponseItem)
        fun onLayoutAddClick(position: Int, itemList: NewProductResponseItem)
        fun onAddClick(position: Int, itemList: NewProductResponseItem)
        fun onSubtractClick(position: Int, itemList: NewProductResponseItem)
    }



    fun filteredList(filteredNames:ArrayList<NewProductResponseItem>){

        this.itemList=filteredNames
        notifyDataSetChanged()

    }




}

