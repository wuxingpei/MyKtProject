package com.example.myapplication.repository

import com.example.myapplication.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(private val api: ApiService) {
    suspend fun login(username: String, password: String) =
        withContext(Dispatchers.IO) {
            api.login(username, password)
        }

    suspend fun loginOut() =
        withContext(Dispatchers.IO) {
            api.loginOut()
        }
}