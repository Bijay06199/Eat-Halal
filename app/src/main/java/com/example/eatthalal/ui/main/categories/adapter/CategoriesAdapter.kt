package com.example.eatthalal.ui.main.categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eatthalal.R
import com.example.eatthalal.databinding.RecyclerviewCategoriesBinding
import com.example.eatthalal.ui.main.categories.response.CategoriesResponse
import kotlinx.android.synthetic.main.recyclerview_categories.view.*

class CategoriesAdapter(private val context: Context, private val listener:OnItemClickListener, var itemList:ArrayList<CategoriesResponse>): RecyclerView.Adapter<CategoriesAdapter.FeaturedViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {

        val view=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_categories,parent,false)
        return FeaturedViewHolder(view)
    }

    override fun getItemCount(): Int=itemList.size

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {


        var name=itemList[position].name

        if (!name.equals("Slider", ignoreCase = true) && !name.equals(
                "Uncategorized", ignoreCase = true) && !name.equals("Slider Banners",ignoreCase = true)
        ){
            holder.categoryName.text=itemList[position].name
        } else {
            holder.clClick.setVisibility(View.GONE)
            holder.clClick.setLayoutParams(RecyclerView.LayoutParams(0, 0))
        }


        Glide.with(holder.categoryImage)
            .load(itemList[position].image?.src)
            .placeholder(R.drawable.place_holder_icon)
            .into(holder.categoryImage)
        holder.clClick.setOnClickListener {
            listener.onSelectListener(holder.adapterPosition,itemList[holder.adapterPosition])
        }
    }

    interface OnItemClickListener{
        fun onSelectListener(position: Int,itemList: CategoriesResponse)
    }


    inner class FeaturedViewHolder(val containerView: View):
        RecyclerView.ViewHolder(containerView){
        val categoryName=containerView.tv_category_name
        val categoryImage=containerView.iv_category_image
        val clClick=containerView.clClick
    }



}