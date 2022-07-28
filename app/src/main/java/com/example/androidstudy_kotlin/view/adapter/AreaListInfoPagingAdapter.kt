package com.example.androidstudy_kotlin.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.data.model.Item
import com.example.androidstudy_kotlin.databinding.ItemAreaInfoBinding

class AreaListInfoPagingAdapter : PagingDataAdapter<Item, AreaListInfoPagingAdapter.ItemViewHolder>(differCallback) {
    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.contentid == newItem.contentid
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
        LayoutInflater.from(parent.context).inflate(R.layout.item_area_info, parent, false)
    ) {
        private val viewBind = ItemAreaInfoBinding.bind(itemView)

        fun bind(data: Item) {
            viewBind.apply {
                tvAreaItemTitle.text = data.title
                tvAreaItemAddr1.text = data.addr1

                ivAreaItem.load(data.firstimage2) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation())
//                    placeholder(R.drawable.ic_launcher_background)
                }

                itemView.setOnClickListener {
                    Log.d("minchae", "title : ${tvAreaItemTitle.text}")
                }
            }
        }
    }
}