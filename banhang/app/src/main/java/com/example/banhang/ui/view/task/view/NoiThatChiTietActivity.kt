package com.example.banhang.ui.view.task.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.banhang.R
import com.example.banhang.databinding.ActivityDangNhapBinding
import com.example.banhang.databinding.ActivityNoiThatChiTietBinding
import com.example.banhang.ui.base.BaseActivityNew
import com.example.banhang.ui.view.task.viewmodel.ViewModelNoiThat

class NoiThatChiTietActivity : BaseActivityNew<ActivityNoiThatChiTietBinding>(){
    override fun getLayoutRes(): Int = R.layout.activity_noi_that_chi_tiet

    private val viewModel : ViewModelNoiThat by viewModels()

    override fun getDataFromIntent() {
        val idnoithat = intent.getStringExtra("noithatid")

        idnoithat?.let { viewModel.getNoiThatCT(it) }
    }

    override fun initData() {
        binding.viewModelNoiThat = viewModel
        binding.executePendingBindings()
    }

    override fun initView() {
        viewModel.noiThatCT.observe(this){
            binding.noithat = it
        }
    }

    override fun setListener() {}

    companion object{
        fun createIntent(context: Context):Intent{
            return Intent(context, NoiThatActivity::class.java)
        }
    }
}