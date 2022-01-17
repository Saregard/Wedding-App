package com.example.weddingapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import java.util.ArrayList

class WishlistRecyclerViewAdapter(
    private val listOfGifts: ArrayList<Wishlist>,
    private val context: Context
) : RecyclerView.Adapter<WishlistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_view_wishlist, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gift: Wishlist = listOfGifts[position]
        holder.title.text = gift.title
    }

    override fun getItemCount(): Int = listOfGifts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: MaterialTextView = itemView.findViewById(R.id.cardViewTextView)

//        companion object {
//            fun create(parent: ViewGroup): ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding =
//                    LayoutRecyclerViewWishlistBinding.inflate(layoutInflater, parent, false)
//                return ViewHolder(binding)
//            }
//        }

    }
}