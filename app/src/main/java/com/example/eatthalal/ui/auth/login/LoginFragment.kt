package com.example.eatthalal.ui.auth.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.andrognito.flashbar.Flashbar
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseFragment
import com.example.eatthalal.constants.Constants
import com.example.eatthalal.databinding.FragmentLoginBinding
import com.example.eatthalal.ui.auth.login.orders.CustomerOrderActivity
import com.example.eatthalal.ui.auth.login.response.CustomerResponse
import com.example.eatthalal.ui.auth.register.RegisterNameActivity
import com.example.eatthalal.ui.main.MainActivity
import com.example.eatthalal.utils.AuthListenerInfo
import com.example.eatthalal.utils.extentions.dangerFlashBar
import com.example.eatthalal.utils.extentions.infoFlashBar
import com.example.eatthalal.utils.extentions.successFlashBar
import com.example.eatthalal.utils.extentions.warningFlashBar
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment() : BaseFragment<FragmentLoginBinding, LoginViewModel>(), AuthListenerInfo {


    var flashbar: Flashbar? = null
    override fun getLayoutId(): Int = R.layout.fragment_login
    override fun getViewModel(): LoginViewModel = loginViewModel
    private val loginViewModel: LoginViewModel by viewModel()

    var itemList = ArrayList<CustomerResponse>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpObservers()

    }

    private fun setUpObservers() {
        with(viewDataBinding) {
            with(loginViewModel) {

            //    getCustomers()
                loginSuccessEvent.observe(viewLifecycleOwner, Observer {

                    progressBar4.visibility=View.GONE

                    var customer=customer
                    var count=customer!!.size

                    var userEmail=eTUserMail.text.toString()

                    customer.forEach {i->
                        var email =i.email

                        if (userEmail.equals(email)) {

                            lLLoginLayout.visibility = View.GONE
                            afterLogin.visibility = View.VISIBLE

                            preferenceManager.setIsLoggedIn(true)


                            var firstName = i.firstName
                            var lastName = i.lastName
                            var number = i.username
                            var address = i.shipping?.address1
                            var address1 = i.shipping?.address2
                            var customerId=i.id

                            preferenceManager.setEmail(email)
                            preferenceManager.setFirstName(firstName)
                            preferenceManager.setLastName(lastName)
                            preferenceManager.setNumber(number)
                            preferenceManager.setAddress(address)
                            preferenceManager.setCustomerId(customerId)

                            var intent=Intent(this@LoginFragment.activity, MainActivity::class.java)
                            startActivity(intent)
                        }

                        else {
                            authListenerInfo?.onInfo("Invalid username")
                        }
                    }



                })

                getCustomers(preferenceManager.getEmail())



                customerDetailEvent.observe(viewLifecycleOwner, Observer {

                    var firstName = customer?.get(0)?.firstName
                    var lastName = customer?.get(0)?.lastName
                    var email = customer?.get(0)?.email
                    var number = customer?.get(0)?.username
                    var address = customer?.get(0)?.shipping?.address1
                    var address1 = customer?.get(0)?.shipping?.address2


                    cl2.setOnClickListener {

                        val intent =
                            Intent(this@LoginFragment.activity, EditprofileActivity::class.java)
                        intent.putExtra(Constants.FirstName, firstName)
                        intent.putExtra(Constants.LastName, lastName)
                        intent.putExtra(Constants.Email, email)
                        intent.putExtra(Constants.Number, number)
                        intent.putExtra(Constants.Address, address)
                        intent.putExtra(Constants.Addrss1, address)
                        startActivity(intent)
                    }


                })
            }
        }
    }

    private fun initView() {


        loginViewModel.authListenerInfo = this
        viewDataBinding.progressBar4.visibility=View.GONE



        with(viewDataBinding) {
            linkClick.setOnClickListener {
                val url = "https://raisetech.com.np/"

                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }

            cl1.setOnClickListener {
                startActivity(
                    Intent(
                        this@LoginFragment.activity,
                        CustomerOrderActivity::class.java
                    )
                )
            }

            tvUserName.setText(preferenceManager.getFirstName())
            if (preferenceManager.getIsLoggedIn()) {
                lLLoginLayout.visibility = View.GONE
                afterLogin.visibility = View.VISIBLE
                progressBar4.visibility=View.GONE
            } else if (!preferenceManager.getIsLoggedIn()) {
                lLLoginLayout.visibility = View.VISIBLE
                afterLogin.visibility = View.GONE

            }

            with(loginViewModel) {
                setupUI(hideKeyboard)


                cl3.setOnClickListener {
                    preferenceManager.setIsLoggedIn(false)
                    lLLoginLayout.visibility = View.VISIBLE
                    afterLogin.visibility = View.GONE
                }

            }
        }


        with(viewDataBinding) {
            btnRegister.setOnClickListener {
                startActivity(Intent(this@LoginFragment.activity, RegisterNameActivity::class.java))

            }
        }
    }


    companion object {
        val TAG = LoginFragment::class.java.simpleName
        fun start(activity: FragmentActivity, containerId: Int) {
            val fragment = LoginFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(TAG)
                .commit()
        }
    }

    override fun onSuccess(message: String) {
        flashbar = successFlashBar(message)
        flashbar?.show()
    }

    override fun onStarted() {
        viewDataBinding.progressBar4.visibility=View.VISIBLE
        var animation= AnimationUtils.loadAnimation(requireContext(),R.anim.rotation_anim)
        animation.setInterpolator ( LinearInterpolator() )
        viewDataBinding.progressBar4.startAnimation(animation)
    }

    override fun onInfo(message: String) {
        flashbar = infoFlashBar(message)
        flashbar?.show()
    }

    override fun onWarning(message: String) {
        flashbar = warningFlashBar(message)
        flashbar?.show()
    }

    override fun onDanger(message: String) {
        flashbar = dangerFlashBar(message)
        flashbar?.show()
    }
}