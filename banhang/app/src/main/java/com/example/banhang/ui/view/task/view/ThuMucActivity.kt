package com.example.banhang.ui.view.task.view

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banhang.R
import com.example.banhang.databinding.ActivityThuMucBinding
import com.example.banhang.ui.base.BaseActivityNew
import com.example.banhang.ui.view.task.adapter.ThuMucAdapter
import com.example.banhang.ui.view.task.model.ThuMuc
import com.example.banhang.ui.view.task.viewmodel.ViewModelLoaiNoiThat
import com.example.banhang.ui.view.task.viewmodel.ViewModelThuMuc

class ThuMucActivity : BaseActivityNew<ActivityThuMucBinding>(){
    override fun getLayoutRes(): Int = R.layout.activity_thu_muc

    val viewModel : ViewModelThuMuc by viewModels()
    val viewModelLNoiThat : ViewModelLoaiNoiThat by viewModels()

    override fun getDataFromIntent() {}

    override fun initData() {}

    override fun initView() {
//        lấy ở cái biến chung ý
        binding.activityThuMuc = viewModel
        binding.viewModelLoaiNoiThat = viewModelLNoiThat
        binding.lifecycleOwner = this

        // Thiết lập adapter cho RecyclerView
        binding.rclThuMuc.layoutManager = LinearLayoutManager(this)
        binding.rclLoaiNoiThat.layoutManager = LinearLayoutManager(this)

        // Gán Adapter cho RecyclerView
//        Thao tác với bên adapter
        binding.rclThuMuc.adapter = viewModel.adapter
        binding.rclLoaiNoiThat.adapter = viewModelLNoiThat.adapter

        // Quan sát dữ liệu
        setUpObservers()

        // Thiết lập sự kiện click cho adapter thuMuc
        ItemClickThuMuc()
    }

    override fun setListener() {}

    private fun setUpObservers(){
        //      Danh sách thư mục
        viewModel.thuMuc.observe(this){
            viewModel.adapter.updateThuMucs(it)
        }

//      Danh sách loại nôi thất
        viewModelLNoiThat.loaiNoiThats.observe(this) { loaiNoiThats ->
            viewModelLNoiThat.adapter.updateLoaiNoiThats(loaiNoiThats)

            binding.txtEmpty.visibility = if (loaiNoiThats.isNullOrEmpty()) View.VISIBLE else View.GONE
            binding.rclLoaiNoiThat.visibility = if (loaiNoiThats.isNullOrEmpty()) View.GONE else View.VISIBLE
        }

    }

    private fun ItemClickThuMuc(){
//        thực hiện click item để hiển thị ds LNoiThat
        viewModel.adapter.setOnItemClickListener { thuMuc ->
            viewModelLNoiThat.getloaiNoiThats(thuMuc._id)
        }
    }

    companion object{
        fun createIntent(context: Context) : Intent{
            return Intent(context, ThuMucActivity::class.java)
        }
    }
}