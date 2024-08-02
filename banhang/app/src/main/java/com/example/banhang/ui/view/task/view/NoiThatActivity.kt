package com.example.banhang.ui.view.task.view

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banhang.R
import com.example.banhang.databinding.ActivityNoiThatBinding
import com.example.banhang.ui.base.BaseActivityNew
import com.example.banhang.ui.view.task.viewmodel.ViewModelNoiThat

class NoiThatActivity : BaseActivityNew<ActivityNoiThatBinding>(){
    override fun getLayoutRes(): Int = R.layout.activity_noi_that

    private val viewModel : ViewModelNoiThat by viewModels()

    override fun getDataFromIntent() {
        val lnoithatid = intent.getStringExtra("loaiNoiThatId")
//        val lnoithatid = "667fbb63d8ea08508373ec1f"
        if (lnoithatid != null) {
            viewModel.getNoiThat(lnoithatid)
        }
        Log.d("NoiThatActivity", "Activity created")
    }

    override fun initData() {
        binding.viewModelNoiThat = viewModel
        binding.lifecycleOwner = this

        binding.rclNoiThat.layoutManager = LinearLayoutManager(this)
        binding.rclNoiThat.adapter = viewModel.adapter

    }

    override fun initView() {
        viewModel.noiThat.observe(this){
            viewModel.adapter.updateNoiThat(it)
        }
    }

    override fun setListener() {

    }

    companion object{
        fun createIntent(context: Context) : Intent {
            return Intent(context, NoiThatActivity::class.java)
        }
    }
}