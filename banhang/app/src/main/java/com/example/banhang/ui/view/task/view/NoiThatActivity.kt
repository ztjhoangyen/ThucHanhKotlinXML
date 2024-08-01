package com.example.banhang.ui.view.task.view

import android.util.Log
import androidx.activity.viewModels
import com.example.banhang.R
import com.example.banhang.databinding.ActivityNoiThatBinding
import com.example.banhang.ui.base.BaseActivityNew
import com.example.banhang.ui.view.task.viewmodel.ViewModelNoiThat

class NoiThatActivity : BaseActivityNew<ActivityNoiThatBinding>(){
    override fun getLayoutRes(): Int = R.layout.activity_noi_that

    private val viewModel : ViewModelNoiThat by viewModels()

    override fun getDataFromIntent() {
        val lnoithatid = intent.getStringExtra("loaiNoiThatId")
        if (lnoithatid != null) {
            viewModel.getNoiThat(lnoithatid)
        }
        Log.d("NoiThatActivity", "Activity created")

    }

    override fun initData() {
        binding.viewModelNoiThat = viewModel
        binding.lifecycleOwner = this

    }

    override fun initView() {

    }

    override fun setListener() {

    }

}