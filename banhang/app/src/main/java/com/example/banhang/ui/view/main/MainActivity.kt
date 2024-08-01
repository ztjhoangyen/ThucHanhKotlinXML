package com.example.banhang.ui.view.main


import com.example.banhang.R
import com.example.banhang.databinding.ActivityMainBinding
import com.example.banhang.ui.base.BaseActivityNew
import com.example.banhang.ui.view.task.view.ThuMucActivity

//MainActivity sẽ là nơi chứa Fragment và thiết lập giao diện chính.
class MainActivity : BaseActivityNew<ActivityMainBinding>(){
    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun getDataFromIntent() {
        // Lấy dữ liệu từ Intent nếu có

        // Khởi tạo dữ liệu
//        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, TaskFragment()) // Thay TaskFragment bằng fragment bạn muốn hiển thị
//                .commit()
//        }
    }

    override fun initData() {
        // Khởi tạo dữ liệu

    }

    override fun initView() {
        // Khởi tạo giao diện
        // Thêm nút để chuyển đến NewActivity
        binding.chuyen.setOnClickListener{
            startActivity(ThuMucActivity.createIntent(this))
//            finish()
        }
    }

    override fun setListener() {
        // Thiết lập các sự kiện tại đây
    }
}