package com.example.banhang.ui.view.task.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banhang.ui.view.task.adapter.LoaiNoiThatAdapter
import com.example.banhang.ui.view.task.data.RetrofitBanHang
import com.example.banhang.ui.view.task.model.LoaiNoiThat
import kotlinx.coroutines.launch

class ViewModelLoaiNoiThat : ViewModel() {
    private val _loaiNoiThats = MutableLiveData<List<LoaiNoiThat>>()
    val loaiNoiThats : LiveData<List<LoaiNoiThat>> = _loaiNoiThats

    val adapter = LoaiNoiThatAdapter(mutableListOf(), this)

    init {
//        getAllloaiNoiThat()
    }

//    fun getAllloaiNoiThat(){
//        viewModelScope.launch {
//            try {
//                val response = RetrofitBanHang().server.getAllloaiNoiThat()
//                if(response.isSuccessful){
//                    val loaiNoiThatList = response.body() ?: emptyList()
//                    _loaiNoiThats.postValue(loaiNoiThatList)
//                    adapter.updateLoaiNoiThats(loaiNoiThatList)
//                }
//            }catch (e : Exception){
//                _loaiNoiThats.postValue(emptyList())
//            }
//        }
//    }

    fun getloaiNoiThats(id : String){
        viewModelScope.launch {
            try {
                val response = RetrofitBanHang().server.getloaiNoiThats(id)
                if(response.isSuccessful){
                    val loaiNoiThatList = response.body() ?: emptyList()
                    _loaiNoiThats.postValue(loaiNoiThatList)
                    adapter.updateLoaiNoiThats(loaiNoiThatList)
                }else{
                    _loaiNoiThats.postValue(emptyList())
                }
            }catch (e : Exception){
                _loaiNoiThats.postValue(emptyList())
            }
        }
    }
}