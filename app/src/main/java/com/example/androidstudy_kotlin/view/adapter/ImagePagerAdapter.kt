package com.example.androidstudy_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.androidstudy_kotlin.R
import com.example.androidstudy_kotlin.network.data.Item
import com.example.androidstudy_kotlin.databinding.ItemImageBinding

class ImagePagerAdapter(val list: ArrayList<Item>): RecyclerView.Adapter<ImagePagerAdapter.ViewHolderPage>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPage {
        return ViewHolderPage(parent)
    }

    override fun onBindViewHolder(holder: ViewHolderPage, position: Int) {
        val viewHolder: ViewHolderPage = holder
        viewHolder.bind(list[position % list.size])
    }

    override fun getItemCount(): Int = list.size

    class ViewHolderPage(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)) {
        private val viewBind = ItemImageBinding.bind(itemView)

        fun bind(data: Item) {
            viewBind.apply {
                itemImage.load(data.firstimage) {
                    crossfade(true)
//                    transformations(RoundedCornersTransformation())
//                    placeholder(R.drawable.ic_launcher_background)
                }
            }
        }
    }
}