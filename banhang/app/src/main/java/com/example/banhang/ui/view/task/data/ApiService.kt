package com.example.banhang.ui.view.task.data

import com.example.banhang.ui.view.task.model.Account
import com.example.banhang.ui.view.task.model.NguoiDungDK
import com.example.banhang.ui.view.task.model.NguoiDungDN
import com.example.banhang.ui.view.task.model.ThuMuc
import com.example.banhang.ui.view.task.model.LoaiNoiThat
import com.example.banhang.ui.view.task.model.NoiThat
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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

    data class YourResponseModel(
        val success: Boolean,
        val message: String,
        val data: Any? // Bạn có thể thay đổi kiểu dữ liệu này theo cấu trúc phản hồi từ server
    )

//    @Multipart
//    @POST("services")
//    fun uploadImage(
//        @Part image: MultipartBody.Part,
//        @Part("description") description: RequestBody
//    ): Call<YourResponseModel>


    @Multipart
    @POST("services")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("serviceName") serviceName: RequestBody,
        @Part("description") description: RequestBody
    ): Call<YourResponseModel>


}