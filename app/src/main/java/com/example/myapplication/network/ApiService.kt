package com.example.myapplication.network

import com.example.myapplication.base.BaseResultData
import com.example.myapplication.entity.UserCollectData
import com.example.myapplication.entity.UserData
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    // 登录
    @POST("/user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String, @Field("password") password: String
    ): Response<BaseResultData<UserData>>

    // 退出
    @GET("/user/logout/json")
    suspend fun loginOut(): BaseResultData<Any?>

    @GET("/lg/collect/list/{page}/json")
    suspend fun order(
        @Path("page") page: Int, @Header("Cookie") cookie: String
    ): BaseResultData<UserCollectData>
}