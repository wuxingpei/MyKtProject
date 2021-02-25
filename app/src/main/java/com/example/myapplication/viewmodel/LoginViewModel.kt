package com.example.myapplication.viewmodel

import android.text.TextUtils
import androidx.lifecycle.ViewModel
import com.example.myapplication.base.BaseResultData
import com.example.myapplication.base.MyApplication
import com.example.myapplication.data.PreferencesHelper
import com.example.myapplication.entity.UserData
import com.example.myapplication.repository.LoginRepository
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    fun login(username: String, psd: String) = flow {
        emit(repository.login(username, psd))
    }
    fun loginOut() = flow {
        emit(repository.loginOut())
    }

    fun saveUser(info: Response<BaseResultData<UserData>>) {
        val cookies = StringBuilder()
        info.headers()
            .filter { TextUtils.equals(it.first, "Set-Cookie") } //笔记 返回所有与指定条件相符的元素列表。
            .forEach { cookies.append(it.second).append(";") }

        val strCookie =
            if (cookies.endsWith(";")) cookies.substring(0, cookies.length - 1)
            else cookies.toString()
        PreferencesHelper.saveCookie(MyApplication.instance, strCookie)
        PreferencesHelper.saveUserId(MyApplication.instance, info.body()?.data?.id ?: 0)
        PreferencesHelper.saveUserName(MyApplication.instance, info.body()?.data?.nickname ?: "")
    }

    fun clearUserInfo() {
        PreferencesHelper.saveCookie(MyApplication.instance, "")
        PreferencesHelper.saveUserId(MyApplication.instance, 0)
        PreferencesHelper.saveUserName(MyApplication.instance, "")
    }
}