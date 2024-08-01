package com.example.banhang.ui.view.task.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.banhang.R
import com.example.banhang.databinding.ActivityDangNhapBinding
import com.example.banhang.ui.base.BaseActivityNew
import com.example.banhang.ui.view.task.viewmodel.ViewModelDangNhap

class DangNhapActivity : BaseActivityNew<ActivityDangNhapBinding>(){
    override fun getLayoutRes(): Int = R.layout.activity_dang_nhap

    val viewModel : ViewModelDangNhap by viewModels()

    override fun getDataFromIntent() {

    }

    override fun initData() {
//        Nếu bạn cần lấy dữ liệu từ ViewModel hoặc API, bạn sẽ thực hiện điều này tại đây.
    }

    override fun initView() {
//        liên kết ViewModel với layout của bạn
//        cho phép layout quan sát và phản ứng với các thay đổi trong ViewModel
        binding.viewModelDangNhap = viewModel
        // Thiết lập giao diện
        viewModel.isLoginErr.observe(this){
            if(it){
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.NguoiDungDNErr.observe(this) { dangnhapErr ->
            binding.txtInputLayoutTaiKhoan.error = dangnhapErr.ten_tai_khoanErr
            binding.txtInputLayoutMatKhau.error = dangnhapErr.mat_khauErr
        }
    }

     override fun setListener() {
        binding.btnDangNhapdangnhap.setOnClickListener {
            viewModel.postLogin()
        }

         binding.btnDangNhapdangky.setOnClickListener {
            startActivity(DangKyActivity.createIntent(this))
             finish()
         }
     }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, DangNhapActivity::class.java)
        }
    }
}