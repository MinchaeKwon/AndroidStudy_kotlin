package com.example.androidstudy_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudy_kotlin.network.data.Item
import com.example.androidstudy_kotlin.databinding.ItemAreaInfoBinding
import com.example.androidstudy_kotlin.view.ui.AreaTabFragmentDirections

class AreaListInfoPagingAdapter : PagingDataAdapter<Item, AreaListInfoPagingAdapter.ItemDataViewHolder>(differCallback) {
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

    // data binding 이용
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemDataViewHolder(ItemAreaInfoBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ItemDataViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class ItemDataViewHolder(private val binding: ItemAreaInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.item = item

            itemView.setOnClickListener {
                val action = AreaTabFragmentDirections.actionAreaTabFragmentToTripDetailFragment(item.contentid!!.toInt(), item.contenttypeid!!.toInt())
                itemView.findNavController().navigate(action)
            }
        }
    }

    // view binding 이용
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(parent)
//
//    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
//        holder.bind(getItem(position)!!)
//    }
//
//    class ItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
//        LayoutInflater.from(parent.context).inflate(R.layout.item_area_info, parent, false)
//    ) {
//        private val viewBind = ItemAreaInfoBinding.bind(itemView)
//
//        fun bind(data: Item) {
//            viewBind.apply {
//                tvAreaItemTitle.text = data.title
//                tvAreaItemAddr1.text = data.addr1
//
//                ivAreaItem.load(data.firstimage2) {
//                    crossfade(true)
//                    transformations(RoundedCornersTransformation())
////                    placeholder(R.drawable.ic_launcher_background)
//                }
//
//                // 상세 화면으로 이동
//                itemView.setOnClickListener {
//                    val action = AreaTabFragmentDirections.actionAreaTabFragmentToAreaDetailFragment(data.contentid!!, data.contenttypeid!!)
//                    itemView.findNavController().navigate(action)
//                }
//            }
//        }
//    }
}