package com.example.eatthalal.splash
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eatthalal.R
import com.example.eatthalal.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
        //4 second splash time
        Handler().postDelayed({
            if (isOnline()){
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(this,"Please make sure that you have internet connection",Toast.LENGTH_LONG).show()
            }

        },4000)
    }

    private fun initView() {



        var animation= AnimationUtils.loadAnimation(this,R.anim.rotation_anim)
        animation.setInterpolator ( LinearInterpolator() )
        iv_icons.startAnimation(animation)
    }

    fun isOnline(): Boolean {
        val cm =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null &&
                cm.activeNetworkInfo!!.isConnectedOrConnecting
    }
}