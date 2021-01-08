package com.example.eatthalal.ui.auth.register

import android.os.Bundle
import androidx.lifecycle.Observer
import com.andrognito.flashbar.Flashbar
import com.example.eatthalal.BR
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseActivity
import com.example.eatthalal.databinding.ActivityRegisterNameBinding
import com.example.eatthalal.utils.AuthListenerInfo
import com.example.eatthalal.utils.extentions.dangerFlashBar
import com.example.eatthalal.utils.extentions.infoFlashBar
import com.example.eatthalal.utils.extentions.successFlashBar
import com.example.eatthalal.utils.extentions.warningFlashBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterNameActivity : BaseActivity<ActivityRegisterNameBinding,RegisterNameViewModel>(),AuthListenerInfo {

    var flashbar: Flashbar?=null
    override fun getLayoutId(): Int =R.layout.activity_register_name
    override fun getViewModel(): RegisterNameViewModel =registerNameViewModel
    private val registerNameViewModel:RegisterNameViewModel by viewModel()
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setUpObservers()

    }

    private fun setUpObservers() {
        with(viewDataBinding){
            with(registerNameViewModel){
                backClickedEvent.observe(this@RegisterNameActivity, Observer {
//                    viewDataBinding.back.setColorFilter(
//                        viewDataBinding.back.context.resources.getColor(R.color.pressed_back),
//                        PorterDuff.Mode.MULTIPLY
//                    )
                    finish()
                })
            }
        }
    }

    private fun initView() {

        registerNameViewModel.authListenerInfo=this
    }

    override fun onSuccess(message: String) {

        flashbar=successFlashBar(message)
        flashbar?.show()

    }

    override fun onStarted() {

    }

    override fun onInfo(message: String) {
        flashbar=infoFlashBar(message)
        flashbar?.show()

    }

    override fun onWarning(message: String) {
        flashbar=warningFlashBar(message)
        flashbar?.show()

    }

    override fun onDanger(message: String) {
        flashbar=dangerFlashBar(message)
        flashbar?.show()

    }
}