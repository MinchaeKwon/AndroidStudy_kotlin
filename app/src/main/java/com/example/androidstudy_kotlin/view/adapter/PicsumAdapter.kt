package com.example.androidstudy_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.androidstudy_kotlin.PracticeApplication.Companion.context
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.databinding.ItemImageBinding
import com.example.androidstudy_kotlin.network.data.ItemPic

class PicsumAdapter : RecyclerView.Adapter<PicsumAdapter.PicsumViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<ItemPic>() {
        override fun areItemsTheSame(oldItem: ItemPic, newItem: ItemPic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemPic, newItem: ItemPic): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsumViewHolder {
        return PicsumViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PicsumViewHolder, position: Int) {
        val viewHolder: PicsumViewHolder = holder
        viewHolder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class PicsumViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)) {
        private val viewBind = ItemImageBinding.bind(itemView)

        fun bind(data: ItemPic) {
            viewBind.apply {
                itemImage.load(data.download_url) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation())
//                    placeholder(R.drawable.ic_launcher_background)
                }

//                val anim = AnimationUtils.loadAnimation(context, R.anim.anim_list_slide_in_right)
//                itemImage.startAnimation(anim)
            }
        }
    }
}