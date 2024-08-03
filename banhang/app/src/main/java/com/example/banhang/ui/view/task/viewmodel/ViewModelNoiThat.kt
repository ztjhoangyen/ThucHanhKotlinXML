package com.example.banhang.ui.view.task.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banhang.ui.view.task.adapter.NoiThatAdapter
import com.example.banhang.ui.view.task.data.RetrofitBanHang
import com.example.banhang.ui.view.task.model.NoiThat
import kotlinx.coroutines.launch

class ViewModelNoiThat : ViewModel() {
    private val _noiThat = MutableLiveData<List<NoiThat>>()
    val noiThat : LiveData<List<NoiThat>> = _noiThat

    private val _noiThatCT = MutableLiveData<NoiThat>()
    val noiThatCT : LiveData<NoiThat> = _noiThatCT

    private val _noiThatErr = MutableLiveData<String?>()
    val noiThatErr: LiveData<String?> = _noiThatErr

    val adapter = NoiThatAdapter(mutableListOf(), this)

    private val _soluong = MutableLiveData(0)
    val soluong : LiveData<Int> get() = _soluong

    fun getNoiThat(id : String){
        viewModelScope.launch {
            try {
                val response = RetrofitBanHang().server.getNoiThat(id)
                if(response.isSuccessful){
                    val noithatList = response.body() ?: emptyList()
                    _noiThat.postValue(noithatList)
                    adapter.updateNoiThat(noithatList)
                }else{
                    _noiThat.postValue(emptyList())
                    _noiThatErr.postValue("Lỗi: ${response.message()}")
                }
            }catch (e : Exception){
                _noiThat.postValue(emptyList())
                _noiThatErr.postValue("Lỗi: ${e.message}")
            }
        }
    }

    fun getNoiThatCT(id : String){
        viewModelScope.launch {
            try {
                val response = RetrofitBanHang().server.getNoiThatCT(id)
                if(response.isSuccessful){
                    _noiThatCT.postValue(response.body())
                }
            }catch (e : Exception){

            }
        }
    }

    fun tangSoLuong(){
//        tại sao phải đặt biến tạm tại vì đỡ phải kiểm tra null nhiều lần
        val currentNoiThatCT = _noiThatCT.value
        if(currentNoiThatCT?.so_luong!! > _soluong.value!!){
            _soluong.value = (_soluong.value ?: 0) + 1
        }
    }

    fun giamSoLuong(){
        if(soluong.value != null && soluong.value!! > 0){
            _soluong.value = _soluong.value!! -1
        }
    }
}