package com.example.banhang.ui.view.task.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banhang.ui.view.task.adapter.ThuMucAdapter
import com.example.banhang.ui.view.task.data.RetrofitBanHang
import com.example.banhang.ui.view.task.model.ThuMuc
import kotlinx.coroutines.launch

class ViewModelThuMuc :ViewModel(){
    private val _thuMucs = MutableLiveData<List<ThuMuc>>()
    val thuMuc : LiveData<List<ThuMuc>> = _thuMucs

    // Chuyển ViewModel vào Adapter
    val adapter = ThuMucAdapter(mutableListOf(), this)

    init {
        getThuMuc()
    }

    fun getThuMuc(){
        viewModelScope.launch {
            try {
                val response = RetrofitBanHang().server.getdanhsach()
                if(response.isSuccessful){
                    val thuMucList = response.body() ?: emptyList()
                    _thuMucs.value =  thuMucList
                    // Cập nhật danh sách vào Adapter
                    adapter.updateThuMucs(thuMucList)
                    Log.d("VM_Login", "Success")
                }
            }catch (e: Exception){
                _thuMucs.value = emptyList()
                Log.d("VM_Login", "Success ${e.message}")
            }
        }
    }

}