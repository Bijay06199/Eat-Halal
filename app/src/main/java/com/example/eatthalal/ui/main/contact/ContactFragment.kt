package com.example.eatthalal.ui.main.contact

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.eatthalal.BR
import com.example.eatthalal.R
import com.example.eatthalal.base.BaseFragment
import com.example.eatthalal.databinding.FragmentContactBinding
import com.example.eatthalal.ui.main.home.HomeFragment
import com.example.eatthalal.utils.GPSTracker
import org.koin.androidx.viewmodel.ext.android.viewModel


class ContactFragment : BaseFragment<FragmentContactBinding, ContactViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_contact
    override fun getViewModel(): ContactViewModel = contactViewModel
    private val contactViewModel: ContactViewModel by viewModel()
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        with(viewDataBinding) {

            tVContact.setOnClickListener {
                var contactIntent = Intent(
                    Intent.ACTION_DIAL, Uri.fromParts(
                        "tel", getActivity()?.getString(R.string.contact1), null
                    )
                )
                startActivity(contactIntent)
            }


            tVMail.setOnClickListener {
                val recepientEmail =
                    requireActivity().getString(R.string.mail) // either set to destination email or leave empty
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.setData(Uri.parse("mailto:" + recepientEmail))
                startActivity(intent)

            }

            tVLocation.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) ==
                    PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    showMap()
                } else {
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        1
                    )
                }

            }

            iVInstagram.setOnClickListener {
                getInstagramAccount()

            }

            iVFacebook.setOnClickListener {

                startActivity(getOpenFacebookIntent())
            }


        }
    }

    private fun showMap() {
        val gps = GPSTracker(requireContext())
        val latitude: Double = gps.getLatitude()
        val longitude: Double = gps.getLongitude()

        val destinationLatitude = 27.692552
        val destinationLongitude = 85.329232
        val uri =
            "http://maps.google.com/maps?saddr=$latitude,$longitude&daddr=$destinationLatitude,$destinationLongitude"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }

    private fun getOpenFacebookIntent(): Intent? {
        return try {
            requireContext().packageManager.getPackageInfo("com.facebook.katana", 0)
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/EatHalal786")
            )
        } catch (e: Exception) {
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/zorbistore")
            )
        }
    }

    private fun getInstagramAccount() {
//        val uri = Uri.parse("https://www.instagram.com/zorbistore")
//        val likeIng = Intent(Intent.ACTION_VIEW, uri)
//
//        likeIng.setPackage("com.instagram.android")
//
//        try {
//            startActivity(likeIng)
//        } catch (e: ActivityNotFoundException) {
//            startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("https://www.instagram.com/zorbistore")
//                )
//            )
//        }
    }


    private fun initView() {
        with(viewDataBinding) {

            linkClick.setOnClickListener {
                val url = "https://raisetech.com.np/"

                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }

            ivBack.setOnClickListener {
                HomeFragment.start(requireActivity(), R.id.main_screen_container)
            }
        }
    }

    companion object {
        val TAG = ContactFragment::class.java.simpleName
        fun start(activity: FragmentActivity, containerId: Int) {
            val fragment = ContactFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(TAG)
                .commit()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showMap()
            }
        }
    }


}