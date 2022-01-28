package com.example.weddingapp

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
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


        val picture = holder.cardViewImageView
        picture.scaleType = ImageView.ScaleType.CENTER_CROP


        Glide
            .with(context)
            .load(gift.firestoreImageUrl)
            .placeholder(R.drawable.banner)
            .into(holder.cardViewImageView)

//        Picasso
//            .get()
//            .load(gift.firestoreImageUrl)
//            .into(holder.cardViewImageView)



    }

    override fun getItemCount(): Int = listOfGifts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val giftTitle: MaterialTextView = itemView.findViewById(R.id.cardViewTitle)
        val giftPrice: MaterialTextView = itemView.findViewById(R.id.cardViewPrice)
        val giftWebsite: MaterialTextView = itemView.findViewById(R.id.cardViewWebsite)
        val cardViewImageView: ImageView = itemView.findViewById(R.id.cardViewImageView)

    }
}