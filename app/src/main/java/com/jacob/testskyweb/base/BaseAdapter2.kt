package com.jacob.testskyweb.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter2<T, Binding : ViewBinding>(
    private val holderLayoutId: Int,
    var data: MutableList<T>,
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding
) : RecyclerView.Adapter<BaseAdapter2<T, Binding>.BaseViewHolder>() {
    private var _binding: Binding? = null
    val binding get() = _binding!!

    @SuppressLint("ServiceCast")
    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        _binding = inflater.invoke(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    fun setDataInList(data: MutableList<T>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    abstract fun onBind(binding: Binding, model: T)

    inner class BaseViewHolder(binding: Binding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: T) {
            onBind(binding, model)
        }
    }
}