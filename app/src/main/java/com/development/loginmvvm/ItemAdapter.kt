package com.development.loginmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.development.loginmvvm.databinding.ItemUiBinding
import com.development.loginmvvm.model.itunes.Result


class ItemAdapter(var mList: MutableList<Result>?, val onItemSelected: (Result?) -> Any?) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: ItemUiBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_ui, parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        mList?.let { it ->
            val data=it[position]
            holder.itemUiBinding.result= data
            holder.itemUiBinding.cvCollection.tag=data
            holder.itemUiBinding.cvCollection.setOnClickListener {view->
                onItemSelected.invoke(view.tag as Result?)
            }
        }

    }

    override fun getItemCount(): Int {
      return   mList?.size ?: 0
    }

    fun setList(mList: MutableList<Result>?) {
        this.mList?.clear()
        this.mList = mList
        notifyDataSetChanged()
    }


    class ItemViewHolder(itemView: ItemUiBinding) : RecyclerView.ViewHolder(itemView.root) {


        var itemUiBinding= itemView

    }
}