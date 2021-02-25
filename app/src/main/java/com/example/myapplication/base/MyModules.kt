package com.example.myapplication.base

import com.example.myapplication.network.RetrofitManager
import com.example.myapplication.repository.LoginRepository
import com.example.myapplication.repository.OrderRepository
import com.example.myapplication.viewmodel.LoginViewModel
import com.example.myapplication.viewmodel.OrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataSourceModule = module {//笔记 viewModel和Repository要在这里注册一下不然会出现找不到类的异常
    single { RetrofitManager.apiService }
}
val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { OrderViewModel(get()) }
}

val repositoryModule = module {
    single { LoginRepository(get()) }
    single { OrderRepository(get()) }
}
