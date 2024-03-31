package com.example.lifetrack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lifetrack.databinding.ItemTaskBinding

class MyAdapter(private val listString: ArrayList<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val value = arrayListOf<TasksActivity>()

    inner class MyViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(value: String) {
            if (value.isNotEmpty()) {
                binding.txName.text = value
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val value = listString.get(position)
        holder.bind(value)
    }

    override fun getItemCount(): Int {
        return listString.size
    }

    fun addStr(string: String) {
        listString.add(string)
        notifyDataSetChanged()
    }
}
