package com.example.eatthalal.di

import com.example.eatthalal.ui.auth.login.EditProfileViewModel
import com.example.eatthalal.ui.auth.login.LoginViewModel
import com.example.eatthalal.ui.auth.login.orders.CustomerOrderViewModel
import com.example.eatthalal.ui.auth.login.orders.orderDetails.OrderDetailsViewModel
import com.example.eatthalal.ui.auth.register.RegisterFinalViewModel
import com.example.eatthalal.ui.auth.register.RegisterNameViewModel
import com.example.eatthalal.ui.auth.register.RegisterPasswordViewModel
import com.example.eatthalal.ui.main.MainViewModel
import com.example.eatthalal.ui.main.cart.CartViewModel
import com.example.eatthalal.ui.main.cart.checkout.CheckOutViewModel
import com.example.eatthalal.ui.main.categories.CategoriesViewModel
import com.example.eatthalal.ui.main.categories.categoriesDetail.CategoriesDetailViewModel
import com.example.eatthalal.ui.main.categories.categoriesDetail.CategoriesItemViewModel
import com.example.eatthalal.ui.main.contact.ContactViewModel
import com.example.eatthalal.ui.main.home.HomeViewModel
import com.example.eatthalal.ui.main.home.eatHalal.EatHalalViewModel
import com.example.eatthalal.ui.main.home.latestProducts.LatestProductViewModel
import com.example.eatthalal.ui.main.home.offers.OffersViewModel
import com.example.eatthalal.ui.main.home.todaysSpecial.TodaysSpeciailViewModel
import com.example.eatthalal.ui.main.shop.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule:Module= module {


    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get(),get(),get(),get(),get(),get(),get()) }
    viewModel { ContactViewModel() }
    viewModel { LoginViewModel(get(),get(),get()) }
    viewModel { CategoriesViewModel(get()) }
    viewModel { RegisterNameViewModel(get()) }
    viewModel { RegisterPasswordViewModel(get()) }
    viewModel { RegisterFinalViewModel(get()) }
    viewModel { CategoriesDetailViewModel(get()) }
    viewModel { CategoriesItemViewModel(get()) }
    viewModel { EditProfileViewModel() }
    viewModel { ShopViewModel(get(),get()) }
    viewModel { CartViewModel() }
    viewModel { CheckOutViewModel(get()) }
    viewModel { LatestProductViewModel(get()) }
    viewModel { EatHalalViewModel(get()) }
    viewModel { OffersViewModel(get()) }
    viewModel { CustomerOrderViewModel(get()) }
    viewModel { OrderDetailsViewModel() }
    viewModel { TodaysSpeciailViewModel(get()) }
}