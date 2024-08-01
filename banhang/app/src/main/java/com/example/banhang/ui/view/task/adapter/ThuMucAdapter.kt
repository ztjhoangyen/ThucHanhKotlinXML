package com.example.banhang.ui.view.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.banhang.databinding.ItemThuMucBinding
import com.example.banhang.ui.view.task.model.ThuMuc
import com.example.banhang.ui.view.task.viewmodel.ViewModelThuMuc

class ThuMucAdapter(
    private var ThuMucs : MutableList<ThuMuc>,
    private var viewModelThuMuc: ViewModelThuMuc
) : RecyclerView.Adapter<ThuMucAdapter.ThuMucViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION // mặc định kh có ai được chọn
    // Thêm listener
    private var itemClickListener: ((ThuMuc) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ThuMucAdapter.ThuMucViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemThuMucBinding.inflate(layoutInflater, parent, false)
        return ThuMucViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThuMucViewHolder, position: Int) {
        holder.bind(ThuMucs[position], position)
    }

    override fun getItemCount(): Int = ThuMucs.size

    inner class ThuMucViewHolder(private val binding: ItemThuMucBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(thuMuc: ThuMuc, position: Int){
//            biến gọi chung bên xml
            binding.itemThuMuc = thuMuc
            // Gán ViewModel vào binding
            binding.viewModel = viewModelThuMuc
            binding.executePendingBindings()

            binding.root.setBackgroundColor(
                if (position == selectedPosition) {
                    ContextCompat.getColor(binding.root.context, android.R.color.holo_red_light)
                } else {
                    ContextCompat.getColor(binding.root.context, android.R.color.white)
                }
            )
//          cập nhật biến đang được chọn dựa vào vị trí
            binding.root.setOnClickListener {
                    selectedPosition = position
                notifyDataSetChanged()
//   Thực hiện hành động đã được định nghĩa cho mục đã nhấn trong danh sách
                itemClickListener?.invoke(thuMuc)
            }

            binding.executePendingBindings()
        }
    }

    // Phương thức để cập nhật danh sách
    fun updateThuMucs(newThuMucs: List<ThuMuc>) {
        ThuMucs.clear()
        ThuMucs.addAll(newThuMucs)
        notifyDataSetChanged()
    }

    // Phương thức để thiết lập item click listener
    fun setOnItemClickListener(listener: (ThuMuc) -> Unit) {
        itemClickListener = listener
    }

}