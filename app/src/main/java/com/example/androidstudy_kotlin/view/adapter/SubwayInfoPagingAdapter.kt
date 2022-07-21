package com.example.androidstudy_kotlin.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.data.model.Item
import com.example.androidstudy_kotlin.databinding.ItemSubwayInfoBinding

// 기존 RecyclerView.Adapter 구현과 동일, DiffUtil을 구현해야함
class SubwayInfoPagingAdapter : PagingDataAdapter<Item, SubwayInfoPagingAdapter.ItemViewHolder>(differCallback) {
    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.subwayStationId == newItem.subwayStationId
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class ItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_subway_info, parent, false)
    ) {
        private val viewBind = ItemSubwayInfoBinding.bind(itemView)

        fun bind(data: Item) {
            viewBind.apply {
                tvSubwayinfoRouteName.text = data.subwayRouteName
                tvSubwayinfoStationId.text = data.subwayStationId
                tvSubwayinfoStationName.text = data.subwayStationName

                itemView.setOnClickListener {
                    Log.d("minchae", "subwayStationName : ${tvSubwayinfoStationName.text}")
                }
            }
        }
    }
}