package com.example.eatthalal.ui.auth.login.orders

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.eatthalal.R
import com.example.eatthalal.ui.auth.login.response.CustomerOrderResponse
import kotlinx.android.synthetic.main.recyclerview_order_items.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CustomerOrderAdapter(private val listener: CustomerOrderAdapter.OnItemClickListener, var itemList:ArrayList<CustomerOrderResponse>): RecyclerView.Adapter<CustomerOrderAdapter.FeaturedViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_order_items,parent,false)
        return FeaturedViewHolder(view)
    }

    override fun getItemCount(): Int=itemList.size

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {


        var date=itemList[position].dateCreated
         var parsed=LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
        val formattedDate=parsed.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))


            holder.order_number.text=itemList[position].number
            holder.orderDate.text=formattedDate
            holder.total.text=itemList[position].total
            holder.status.text=itemList[position].status

        if (holder.status.text.equals("processing")){
            holder.status.setTextColor(R.color.yellow)

        }
        else if(holder.status.text.equals("completed")){
            holder.status.setTextColor(R.color.green)
            }


        holder.root.setOnClickListener {
            listener.onItemSelect(holder.adapterPosition, itemList[holder.adapterPosition])


        }



    }


    inner class FeaturedViewHolder(val containerView: View):
        RecyclerView.ViewHolder(containerView){
        val order_number=containerView.textView4
        val orderDate=containerView.textView11
        var total=containerView.textView5
        var status=containerView.textView26
        var root=containerView.root

    }

    interface OnItemClickListener {
        fun onItemSelect(position: Int, itemList: CustomerOrderResponse)

    }



}