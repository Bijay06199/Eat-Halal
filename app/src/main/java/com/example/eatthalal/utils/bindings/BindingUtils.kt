package com.example.eatthalal.utils.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.eatthalal.R

object BindingUtils {

    @BindingAdapter("intImageSrc")
    @JvmStatic
    fun setImageSrc(imageView: ImageView, imageSrc: Int) {
        imageView.setImageResource(imageSrc)
    }

    @BindingAdapter("imgImageSrc")
    @JvmStatic
    fun setImageSrc(imageView: ImageView, imageSrc: String) {
        with(imageView) {
            Glide.with(context)
                .load(imageSrc)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.logo)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)

                )

                .into(imageView)


        }
    }
}