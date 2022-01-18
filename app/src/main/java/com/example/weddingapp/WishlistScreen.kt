package com.example.weddingapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weddingapp.databinding.ActivityOnskelistaScreenBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.*
import android.content.Intent
import android.net.Uri
import androidx.cardview.widget.CardView
import com.google.android.material.card.MaterialCardView


class WishlistScreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityOnskelistaScreenBinding
    private lateinit var giftArrayList: ArrayList<Wishlist>
    private lateinit var myAdapter: WishlistRecyclerViewAdapter
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnskelistaScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.wishlistRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        giftArrayList = arrayListOf()
        myAdapter = WishlistRecyclerViewAdapter(giftArrayList, this)
        recyclerView.adapter = myAdapter


        addButton()
        eventChangeListener()

    }

    private fun eventChangeListener() {
        db.collection("wishlist")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            giftArrayList.add(dc.document.toObject(Wishlist::class.java))
                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }
            })
    }

    private fun addButton() {
        binding.wishlistAddButton.setOnClickListener {
            val inflater: LayoutInflater =
                getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.add_gift, null)

            val popupWindow = PopupWindow(
                view,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                true
            )

            popupWindow.animationStyle = R.style.popup_window_animation

            popupWindow.elevation = 10.0F
            TransitionManager.beginDelayedTransition(binding.root)
            popupWindow.showAtLocation(
                binding.root, Gravity.CENTER, 0, 0
            )

            val cancelButton = view.findViewById<Button>(R.id.cancelButton)
            val saveGiftButton = view.findViewById<Button>(R.id.saveGiftButton)
            val titleEditText = view.findViewById<TextInputLayout>(R.id.giftTitle)
            val priceEditText = view.findViewById<TextInputLayout>(R.id.giftPrice)
            val websiteEditText = view.findViewById<TextInputLayout>(R.id.giftWebsite)


            saveGiftButton.setOnClickListener {
                if (titleEditText.editText?.text?.isEmpty() == true) {
                    titleEditText.error = "Fyll i en titel"
                } else
                    addData(
                        titleEditText.editText?.text.toString(),
                        priceEditText.editText?.text.toString() + " SEK",
                        websiteEditText.editText?.text.toString()
                    )
                popupWindow.dismiss()

            }
            cancelButton.setOnClickListener {
                popupWindow.dismiss()
            }
        }
    }

    private fun addData(giftTitle: String, giftPrice: String, giftWebsite: String) {
        val gift: MutableMap<String, String> = HashMap()

        gift["giftTitle"] = giftTitle
        gift["giftPrice"] = giftPrice
        gift["giftWebsite"] = giftWebsite

        db.collection("wishlist")
            .add(gift)
            .addOnSuccessListener {
                Toast.makeText(this, "This was a success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "This was a failure", Toast.LENGTH_SHORT).show()
            }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}