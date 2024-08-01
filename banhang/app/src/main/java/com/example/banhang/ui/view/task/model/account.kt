package com.example.banhang.ui.view.task.model

data class Account (
    val _id: String,
    val ten_tai_khoan: String,
    val email: String,
    val mat_khau: String,
    val ho_ten: String,
    val dia_chi: String,
    val so_dien_thoai: String,
    val role: Boolean,
    val socketId: String
)

data class NguoiDungDN (
    val ten_tai_khoan: String,
    val mat_khau: String
)

data class NguoiDungDNErr(
    var ten_tai_khoanErr: String? = null,
    var mat_khauErr: String? = null
)

data class NguoiDungDK (
    val ten_tai_khoan: String,
    val email: String,
    val mat_khau: String,
    val ho_ten: String,
    val role: Boolean
)

data class NguoiDungDKErr(
    val ten_tai_khoan: String?,
    val email: String?,
    val mat_khau: String?,
    val ho_ten: String?
)