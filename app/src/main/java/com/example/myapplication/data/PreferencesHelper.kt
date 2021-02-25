package com.example.myapplication.data

import android.content.Context
import com.example.myapplication.utils.getInteger
import com.example.myapplication.utils.getString
import com.example.myapplication.utils.saveInteger
import com.example.myapplication.utils.saveString

object PreferencesHelper {
    private const val USER_KEY_ID = "user.id"
    private const val USER_KEY_NAME = "user.name"
    private const val USER_KEY_COOKIE = "user.cookie"

    fun saveUserId(context: Context, id: Int) = context.saveInteger(USER_KEY_ID, id)

    fun hasLogin(context: Context) = context.getInteger(USER_KEY_ID) > 0

    fun saveUserName(context: Context, name: String) = context.saveString(USER_KEY_NAME, name)

    fun getUserName(context: Context) = context.getString(USER_KEY_NAME)

    fun saveCookie(context: Context, cookie: String) = context.saveString(USER_KEY_COOKIE, cookie)

    fun getCookie(context: Context) = context.getString(USER_KEY_COOKIE)

}