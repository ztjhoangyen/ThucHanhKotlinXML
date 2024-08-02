package com.example.banhang.ui.view.task.data

import com.example.banhang.ui.view.task.model.Account
import com.example.banhang.ui.view.task.model.NguoiDungDK
import com.example.banhang.ui.view.task.model.NguoiDungDN
import com.example.banhang.ui.view.task.model.ThuMuc
import com.example.banhang.ui.view.task.model.LoaiNoiThat
import com.example.banhang.ui.view.task.model.NoiThat
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("dangkyMM")
    suspend fun postdangky(@Body NguoiDungDK : NguoiDungDK): Response<Void>

    @POST("dangnhap")
    suspend fun postdangnhap(@Body NguoiDungDN : NguoiDungDN): Response<Account>

    @GET("danhMuc")
    suspend fun getdanhsach() : Response<List<ThuMuc>>

    @GET("AllloaiNoiThat")
    suspend fun getAllloaiNoiThat() : Response<List<LoaiNoiThat>>

    @GET("loaiNoiThat/{id}")
    suspend fun getloaiNoiThats(@Path("id") id : String) : Response<List<LoaiNoiThat>>

    @GET("noiThat/{id}")
    suspend fun getNoiThat(@Path("id") id : String) : Response<List<NoiThat>>

    @GET("noiThatCT/{id}")
    suspend fun getNoiThatCT(@Path("id") id : String) : Response<NoiThat>



}