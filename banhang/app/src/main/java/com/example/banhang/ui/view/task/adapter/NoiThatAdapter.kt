package com.example.banhang.ui.view.task.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banhang.databinding.ItemNoiThatBinding
import com.example.banhang.ui.view.task.model.NoiThat
import com.example.banhang.ui.view.task.view.NoiThatChiTietActivity
import com.example.banhang.ui.view.task.viewmodel.ViewModelNoiThat

class NoiThatAdapter(
    private var NoiThat : MutableList<NoiThat>,
    private var viewModelNoiThat: ViewModelNoiThat
) : RecyclerView.Adapter<NoiThatAdapter.NoiThatViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NoiThatAdapter.NoiThatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoiThatBinding.inflate(layoutInflater, parent, false)
        return NoiThatViewHolder(binding)
    }
//CHỨ Ý: lỗi liên quan đến hàm này thì là liên quan đến kiểu dữ liệu và xml
    override fun onBindViewHolder(holder: NoiThatAdapter.NoiThatViewHolder, position: Int) {
        holder.bind(NoiThat[position], position)
    }

    override fun getItemCount(): Int = NoiThat.size

    inner class NoiThatViewHolder(private val binding: ItemNoiThatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(noiThat: NoiThat, position: Int){
            binding.noiThats = noiThat
            binding.executePendingBindings()

//            Intent
            binding.root.setOnClickListener{
                val context = binding.root.context
                val intent = Intent(context, NoiThatChiTietActivity::class.java)
                intent.putExtra("noithatid", noiThat._id)
                context.startActivity(intent)

            }
        }
    }

    fun updateNoiThat(newlnoiThat: List<NoiThat>){
        NoiThat.clear()
        NoiThat.addAll(newlnoiThat)
        notifyDataSetChanged()
    }

}