package com.example.weddingapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.system.Os.uname
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.firestore.FirebaseFirestore


class WishlistRecyclerViewAdapter(
    private val listOfGifts: ArrayList<Wishlist>,
    private val context: Context
) : RecyclerView.Adapter<WishlistRecyclerViewAdapter.ViewHolder>() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_view_wishlist, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(listOfGifts[position], position)
    }

    override fun getItemCount(): Int = listOfGifts.size

    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(index: Int, documentId: String) {
        listOfGifts.removeAt(index)
        notifyDataSetChanged()

        db.collection("wishlist").document(documentId)
            .delete()
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val giftTitle: MaterialTextView = itemView.findViewById(R.id.cardViewTitle)
        private val giftPrice: MaterialTextView = itemView.findViewById(R.id.cardViewPrice)
        private val giftExtraText: MaterialTextView = itemView.findViewById(R.id.cardViewExtraText)
        private val cardViewImageView: ImageView = itemView.findViewById(R.id.cardViewImageView)
        private val giftCardView: MaterialCardView = itemView.findViewById(R.id.giftCardView)
        private val deleteButton: ImageView = itemView.findViewById(R.id.deleteImageView)

        fun bind(gift: Wishlist, index: Int) {
            giftTitle.text = gift.giftTitle
            giftPrice.text = gift.giftPrice
            giftExtraText.text = gift.giftExtraText

            giftCardView.setOnClickListener {
                if (!gift.giftWebsite.contains("http://")){
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://${gift.giftWebsite}"))
                    startActivity(context,browserIntent, null)
                }else {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(gift.giftWebsite))
                    startActivity(context,browserIntent, null)
                }
            }
            deleteButton.setOnClickListener {
                deleteItem(index,gift.giftTitle)
            }

            val picture = cardViewImageView
            picture.scaleType = ImageView.ScaleType.CENTER_CROP
            Glide.with(context).load(gift.firestoreImageUrl).into(cardViewImageView)
        }
    }
}