package com.aridev.applokionboarding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.aridev.applokionboarding.databinding.ItemOnboardingBinding
import com.aridev.applokionboarding.model.ItemOnBoarding

class AdapterOnboarding(var list : List<ItemOnBoarding>) : RecyclerView.Adapter<AdapterOnboarding.MyViewHolder>() {


    inner class MyViewHolder(val binding : ItemOnboardingBinding) : ViewHolder(binding.root) {
        fun bind(item : ItemOnBoarding) {
            binding.imgOnboarding.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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