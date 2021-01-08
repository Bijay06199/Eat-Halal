package com.example.eatthalal.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eatthalal.R
import com.example.eatthalal.ui.main.shop.response.NewProductResponseItem
import kotlinx.android.synthetic.main.banner_layout.view.*

class BannerAdapter(  var itemList:ArrayList<NewProductResponseItem>): RecyclerView.Adapter<BannerAdapter.FeaturedViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.banner_layout,parent,false)
        return FeaturedViewHolder(view)
    }

    override fun getItemCount(): Int=itemList.size

    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {




        var images=itemList[position].images

        for(i in 0 until images!!.size){
            var imageUrl = images[i]?.src


            Glide.with(holder.bannerImage)
                .load(imageUrl)
                .placeholder(R.drawable.place_holder_icon)
                .into(holder.bannerImage)
        }

    }




    inner class FeaturedViewHolder(val containerView: View):
        RecyclerView.ViewHolder(containerView){

        val bannerImage=containerView.banner_image_view

    }



}