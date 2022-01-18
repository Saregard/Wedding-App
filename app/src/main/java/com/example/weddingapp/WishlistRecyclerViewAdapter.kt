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
        holder.giftTitle.text = gift.giftTitle
        holder.giftPrice.text = gift.giftPrice
        holder.giftWebsite.text = gift.giftWebsite
    }

    override fun getItemCount(): Int = listOfGifts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val giftTitle: MaterialTextView = itemView.findViewById(R.id.cardViewTitle)
        val giftPrice: MaterialTextView = itemView.findViewById(R.id.cardViewPrice)
        val giftWebsite: MaterialTextView = itemView.findViewById(R.id.cardViewWebsite)

    }
}