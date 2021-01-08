package com.example.eatthalal.ui.auth.login

import android.os.Bundle
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseActivity
import com.example.eatthalal.constants.Constants
import com.example.eatthalal.databinding.ActivityEditprofileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditprofileActivity : BaseActivity<ActivityEditprofileBinding,EditProfileViewModel>() {

    override fun getLayoutId(): Int =R.layout.activity_editprofile
    override fun getViewModel(): EditProfileViewModel =editProfileViewModel
    private val editProfileViewModel:EditProfileViewModel by viewModel()

    var first_Name:String?=null
    var last_Name:String?=null
    var email_pass:String?=null
    var number_pass:String?=null
    var address_pass:String?=null
    var address1_pass:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        first_Name=intent.getStringExtra(Constants.FirstName)
        last_Name=intent.getStringExtra(Constants.LastName)
        email_pass=intent.getStringExtra(Constants.Email)
        number_pass=intent.getStringExtra(Constants.Number)
        address_pass=intent.getStringExtra(Constants.Address)
        address1_pass=intent.getStringExtra(Constants.Addrss1)

        initView()
        setUpObservers()
    }

    private fun setUpObservers() {
        with(viewDataBinding){
            with(editProfileViewModel){




            }
        }
    }

    private fun initView() {
        with(viewDataBinding){


            tvFirstName.setText(preferenceManager.getFirstName())
           firstName.setText(first_Name)
            lastName.setText(last_Name)
            mobileNumber.setText(number_pass)
            email.setText(email_pass)
            address.setText(address_pass)
            address1.setText(address_pass)
            topBar.setOnClickListener {
                finish()
            }
        }
    }
}