package com.example.banhang.ui.view.task.view

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import com.example.banhang.R
import com.example.banhang.databinding.ActivityDangKyBinding
import com.example.banhang.ui.base.BaseActivityNew
import com.example.banhang.ui.view.task.model.NguoiDungDKErr
import com.example.banhang.ui.view.task.viewmodel.ViewModelDangKy

class DangKyActivity : BaseActivityNew<ActivityDangKyBinding>(){
    override fun getLayoutRes() : Int = R.layout.activity_dang_ky

    val viewModel : ViewModelDangKy by viewModels()

    override fun getDataFromIntent() {}

    override fun initData() {}

    override fun initView() {
        binding.viewModelDangKy = viewModel

        binding.lifecycleOwner = this

        viewModel.isDangKyErr.observe(this){
            if(it){
                showToast("Đăng ký thành công")
            }else{
                showToast("Đăng ký thất bại")
                viewModel.NguoiDungDKErr.observe(this) { err ->
                    updateErrors(err)
                }
            }
        }
    }

    override fun setListener() {
        binding.btnDangKyDangky.setOnClickListener {
            viewModel.postDangKy()
        }
    }

    private fun updateErrors(err: NguoiDungDKErr) {
        binding.txtLayoutTaiKhoan.error = err.ten_tai_khoan
        binding.txtLayoutEmail.error = err.email
        binding.txtLayoutMatKhau.error = err.mat_khau
        binding.txtLayoutHoTen.error = err.ho_ten
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, DangKyActivity::class.java)
        }
    }
}