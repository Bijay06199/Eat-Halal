package com.example.eatthalal.ui.main.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.eatthalal.R
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        var image=intent.getStringExtra("images")


        Glide.with(image_view)
            .load(image)
            .placeholder(R.drawable.ic_empty_image)
            .into(image_view)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}