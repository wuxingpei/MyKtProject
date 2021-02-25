package com.example.myapplication.base

data class BaseResultData<T>(
    val data:T,
    val errorCode: Int,
    val errorMsg: String
)