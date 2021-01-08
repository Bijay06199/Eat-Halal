package com.example.eatthalal.ui.main.shop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eatthalal.R
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import kotlinx.android.synthetic.main.product_items_recyclerview.view.*

class ShoppingAdapter(private var listener:OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val ITEM = 0
    private val LOADING = 1

    private var itemList: MutableList<NewProductResponseItem> = ArrayList()
    private var isLoadingAdded: Boolean = false

    fun setProduct(productList: MutableList<NewProductResponseItem>) {
        this.itemList = productList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ITEM -> {
                val item = inflater.inflate(R.layout.product_items_recyclerview, parent, false)
                ViewHolder(item)
            }

            else -> throw IllegalArgumentException("Wrong view type")
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemList.size - 1 && isLoadingAdded) LOADING else ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM -> {
                var images=itemList[position].images

                for(i in 0 until images!!.size){
                    var imageUrl = images[i]?.src


                    Glide.with(holder.itemView.iv_item)
                        .load(imageUrl)
                        .placeholder(R.drawable.ic_empty_image)
                        .into(holder.itemView.iv_item)
                }


                holder.itemView.tv_product_name.text = itemList[position].name
                holder.itemView.tv_price.text = itemList[position].price

                holder.itemView.root.setOnClickListener {
                    listener.onItemSelect(holder.adapterPosition, itemList[holder.adapterPosition])
                }


//                holder.root.setOnClickListener {
//                    listener.onItemSelect(holder.adapterPosition, itemList[holder.adapterPosition])
//                    listener.onLayoutAddClick(holder.adapterPosition, itemList[holder.adapterPosition])
//                    listener.onAddClick(holder.adapterPosition, itemList[holder.adapterPosition])
//                    listener.onSubtractClick(holder.adapterPosition, itemList[holder.adapterPosition])
//
//                }

//                holder.layoutAdd.setOnClickListener {
//                    holder.layoutAdd.visibility = View.GONE
//                    holder.addMain.visibility = View.GONE
//                    holder.addSubtract.visibility = View.VISIBLE
////                    holder.cart_quantity.setText("" + count++)
//
//
//                }

//                holder.addMain.setOnClickListener {
//
//                    holder.layoutAdd.visibility = View.GONE
//                    holder.addMain.visibility = View.GONE
//                    holder.addSubtract.visibility = View.VISIBLE
////                    holder.cart_quantity.setText("" + count++)
//
//                }

//                holder.add.setOnClickListener {
//
//                    holder.layoutAdd.visibility = View.GONE
//                    holder.addMain.visibility = View.GONE
//                    holder.subtract.visibility = View.VISIBLE
//                    holder.cart_quantity.visibility = View.VISIBLE
////                    holder.cart_quantity.setText("" + count++)
//
//                }

//                holder.subtract.setOnClickListener {
//
////                    holder.cart_quantity.setText("" + count--)
//
//                }
//
//                if (holder.cart_quantity.text == "0") {
//                    holder.layoutAdd.visibility = View.VISIBLE
//                    holder.addMain.visibility = View.VISIBLE
//                    holder.addSubtract.visibility = View.GONE
//
//                } else {
//                    holder.layoutAdd.visibility = View.GONE
//                    holder.addMain.visibility = View.GONE
//                    holder.addSubtract.visibility = View.VISIBLE
//
//
//                }
            }
            LOADING -> {
            }
        }
    }

    fun add(r: NewProductResponseItem) {
        itemList.add(r)
        notifyItemInserted(itemList.size - 1)
    }

    fun addAll(moveMovies: List<NewProductResponseItem>) {
        for (result in moveMovies) {
            add(result)
        }
    }

    fun remove(r: NewProductResponseItem?) {
        val position = itemList.indexOf(r)
        if (position > -1) {
            itemList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        isLoadingAdded = false
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

//    fun addLoadingFooter() {
//        isLoadingAdded = true
//        add(NewProductResponseItem())
//    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = itemList.size - 1
        val result = getItem(position)

        if (result != null) {
            itemList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun getItem(position: Int): NewProductResponseItem? {
        return itemList.get(position)
    }

    inner class ViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {
        val productName = containerView.tv_product_name
        val productImage = containerView.iv_item
        val productPrice = containerView.tv_price
        val root = containerView.root
        val layoutAdd = containerView.layout_add
        val subtract = containerView.cl_subtract
        val add = containerView.cl_add
        val addSubtract = containerView.add_subtract_button
        val addMain = containerView.cl_add_main
        val cart_quantity = containerView.tv_cart_quantity

    }

    interface OnItemClickListener {
        fun onItemSelect(position: Int, itemList: NewProductResponseItem)

    }


}