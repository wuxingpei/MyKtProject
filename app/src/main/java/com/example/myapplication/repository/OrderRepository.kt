package com.example.myapplication.repository

import com.example.myapplication.base.MyApplication
import com.example.myapplication.data.PreferencesHelper
import com.example.myapplication.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OrderRepository(private val api: ApiService) {
    suspend fun order(page: Int) =
        withContext(Dispatchers.IO) {
            val cookie = PreferencesHelper.getCookie(MyApplication.instance)
            api.order(page, cookie)
        }
}