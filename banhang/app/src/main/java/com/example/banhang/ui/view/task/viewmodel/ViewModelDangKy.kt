package com.example.banhang.ui.view.task.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banhang.ui.view.task.data.RetrofitBanHang
import com.example.banhang.ui.view.task.model.NguoiDungDK
import com.example.banhang.ui.view.task.model.NguoiDungDKErr
import com.example.banhang.ui.view.task.model.NguoiDungDNErr
import kotlinx.coroutines.launch

class ViewModelDangKy : ViewModel(){
    val  tenTaiKhoan = MutableLiveData<String>()
    val  matKhau = MutableLiveData<String>()
    val  email = MutableLiveData<String>()
    val  hoten = MutableLiveData<String>()

    private val _NguoiDungDKErr = MutableLiveData<NguoiDungDKErr>()
    val NguoiDungDKErr : LiveData<NguoiDungDKErr> = _NguoiDungDKErr

    private val _isDangKyErr = MutableLiveData<Boolean>()
    val isDangKyErr : LiveData<Boolean> = _isDangKyErr

    fun postDangKy(){
        val userDK = NguoiDungDK(tenTaiKhoan.value ?: "", email.value ?: "", matKhau.value  ?: "", hoten.value ?: "", false)

        viewModelScope.launch {
            try {
                val response = RetrofitBanHang().server.postdangky(userDK)
                if(response.isSuccessful){
                    _isDangKyErr.postValue(true)
                    _NguoiDungDKErr.postValue(NguoiDungDKErr("", "", "", ""))
                    clearFields()
                }else{
                    val error = NguoiDungDKErr(
                        ten_tai_khoan = when {
                            tenTaiKhoan.value.isNullOrEmpty() -> "Chưa nhập tên tài khoản"
                            response.code() == 423 -> response.errorBody()?.string()
                            response.code() == 409 -> response.errorBody()?.string()
                            else -> null
                        },
                        email = when {
                            email.value.isNullOrEmpty() -> "Chưa nhập email"
                            response.code() == 409 -> response.errorBody()?.string()
                            else -> null
                        },
                        mat_khau = when {
                            matKhau.value.isNullOrEmpty() -> "Chưa nhập mật khẩu"
                            response.code() == 422 -> response.errorBody()?.string()
                            else -> null
                        },
                        ho_ten = if (hoten.value.isNullOrEmpty()) "Chưa nhập họ tên" else null
                    )
                    _NguoiDungDKErr.postValue(error)
                    _isDangKyErr.postValue(false)
                }

            }catch (e: Exception){
                _isDangKyErr.postValue(false)
            }
        }
    }

    fun clearFields() {
        tenTaiKhoan.postValue("")
        matKhau.postValue("")
        email.postValue("")
        hoten.postValue("")
    }
}