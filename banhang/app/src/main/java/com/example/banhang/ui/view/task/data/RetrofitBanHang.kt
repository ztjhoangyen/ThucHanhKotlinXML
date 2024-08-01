package com.example.banhang.ui.view.task.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBanHang {
    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.206:3000/api/")
//        .baseUrl("http://192.168.1.19:3000/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val server: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}