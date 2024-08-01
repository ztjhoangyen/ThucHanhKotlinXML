package com.example.banhang.ui.view.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banhang.ui.view.task.data.RetrofitBanHang
import com.example.banhang.ui.view.task.model.Account
import com.example.banhang.ui.view.task.model.NguoiDungDN
import com.example.banhang.ui.view.task.model.NguoiDungDNErr
import kotlinx.coroutines.launch

class ViewModelDangNhap : ViewModel(){
    val tenTaiKhoan = MutableLiveData<String>()
    val matKhau = MutableLiveData<String>()

    private val _acc = MutableLiveData<Account>()
    val acc: LiveData<Account> = _acc

    private val _NguoiDungDNErr = MutableLiveData<NguoiDungDNErr>()
    val NguoiDungDNErr : LiveData<NguoiDungDNErr> = _NguoiDungDNErr

    private val _isLoginErr = MutableLiveData<Boolean>()
    val isLoginErr : LiveData<Boolean> = _isLoginErr

    // Tạo các thuộc tính riêng lẻ cho tên tài khoản và mật khẩu
    // để có thể dang buộc dữ liệu 2 chiều, bên kia thay đổi khi nhập thì bên này cũng thay đổi, kiểu thay đổi khi hiển thị lên màn hinh ý

    fun postLogin() {
        val userDN = NguoiDungDN(tenTaiKhoan.value ?: "", matKhau.value ?: "")
        viewModelScope.launch {
            try {
                val response = RetrofitBanHang().server.postdangnhap(userDN)
                if (response.isSuccessful) {
                    _acc.postValue(response.body())
                    _isLoginErr.postValue(true)
                    NguoiDungDNErr(
                        ten_tai_khoanErr = "",
                        mat_khauErr  = ""
                    )
                 } else {
                    val error = NguoiDungDNErr(
                        ten_tai_khoanErr = if (response.code() == 404) "Tên tài khoản không tồn tại" else null,
                        mat_khauErr = if (response.code() == 400 || response.code() == 404) "Mật khẩu không chính xác" else null
                    )
                    _NguoiDungDNErr.postValue(error)
                    _isLoginErr.postValue(false)
                }
            } catch (e: Exception) {
                _isLoginErr.postValue(false)
            }
        }
    }
}