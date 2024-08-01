package com.example.banhang.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivityNew<T : ViewDataBinding> : BaseActivityBlank() {
    //   getLayoutRes(): Phương thức trừu tượng để cung cấp layout resource ID cho Activity.
    abstract fun getLayoutRes() : Int

    // Các phương thức trừu tượng để khởi tạo dữ liệu và giao diện của Activity.
    abstract fun getDataFromIntent()
    abstract fun initData()
    abstract fun initView()
    abstract fun setListener()

    // Biến để lưu trữ và quản lý ViewDataBinding cho Activity.
    lateinit var binding: T

  // Phương thức này được gọi khi Activity bắt đầu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doFirstMethod()
        binding = DataBindingUtil.setContentView(this, getLayoutRes())

        afterSetContentView()
        getDataFromIntent()
        initData()
        initView()
        setListener()

        enableEdgeToEdge()
    }

    open fun doFirstMethod() {}

    open fun afterSetContentView() {}

    fun showToast(mess: String?) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
    }

}