package com.example.banhang.ui.view.task.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banhang.ui.view.task.data.RetrofitBanHang
import com.example.banhang.ui.view.task.model.NoiThat
import kotlinx.coroutines.launch

class ViewModelNoiThat : ViewModel() {
    private val _noiThat = MutableLiveData<NoiThat>()
    val noiThat : LiveData<NoiThat> = _noiThat

    private val _noiThatErr = MutableLiveData<String?>()
    val noiThatErr: LiveData<String?> = _noiThatErr

    fun getNoiThat(id : String){
        viewModelScope.launch {
            try {
                val response = RetrofitBanHang().server.getNoiThat(id)
                if(response.isSuccessful){
                    _noiThat.postValue(response.body())
                    Log.d("OKOKO", "${response.body()}")
                }else{
                    _noiThatErr.postValue("Lỗi: ${response.message()}")
                }
            }catch (e : Exception){
                _noiThatErr.postValue("Lỗi: ${e.message}")
            }
        }
    }
}