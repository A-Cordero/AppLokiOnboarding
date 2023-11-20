package com.aridev.applokionboarding.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aridev.applokionboarding.databinding.ItemDotBinding

class AdapterDots(var list : List<Boolean>) : RecyclerView.Adapter<AdapterDots.MyViewHolder>(){

    inner class MyViewHolder(val binding : ItemDotBinding) : ViewHolder(binding.root) {
        fun bind(item : Boolean) {
            if(item) {
                binding.dotSelected.visibility = View.VISIBLE
                binding.dotUnselected.visibility = View.GONE
            } else {
                binding.dotSelected.visibility = View.GONE
                binding.dotUnselected.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemDotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun update() {
        notifyDataSetChanged()
    }
}