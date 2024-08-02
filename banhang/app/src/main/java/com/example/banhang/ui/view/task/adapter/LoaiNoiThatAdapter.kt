package com.example.banhang.ui.view.task.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banhang.databinding.ItemLoainoithatBinding
import com.example.banhang.ui.view.task.model.LoaiNoiThat
import com.example.banhang.ui.view.task.view.NoiThatActivity
import com.example.banhang.ui.view.task.viewmodel.ViewModelLoaiNoiThat

//Thực hiện click item ở đây là uy tín
class LoaiNoiThatAdapter(
    private var loaiNoiThat : MutableList<LoaiNoiThat>,
    private var viewModelLoaiNoiThat: ViewModelLoaiNoiThat
): RecyclerView.Adapter<LoaiNoiThatAdapter.LoaiNoithatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoaiNoithatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLoainoithatBinding.inflate(layoutInflater, parent, false)
        return LoaiNoithatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoaiNoithatViewHolder, position: Int) {
        holder.bind(loaiNoiThat[position], position)
    }

    override fun getItemCount(): Int = loaiNoiThat.size

    inner class LoaiNoithatViewHolder(private val binding: ItemLoainoithatBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(loaiNoiThat: LoaiNoiThat, position: Int){
            binding.loaiNoiThat = loaiNoiThat
            binding.viewModel = viewModelLoaiNoiThat
            binding.executePendingBindings()

            // Tạo Intent để chuyển đến Activity mới
            binding.root.setOnClickListener{
                val context = binding.root.context

                val intent = Intent(context, NoiThatActivity::class.java)
                intent.putExtra("loaiNoiThatId", loaiNoiThat._id)
                context.startActivity(intent)
            }
        }
    }

    fun updateLoaiNoiThats(newlnoithat: List<LoaiNoiThat>){
        loaiNoiThat.clear()
        loaiNoiThat.addAll(newlnoithat)
        notifyDataSetChanged()
    }
}