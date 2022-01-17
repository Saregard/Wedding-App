package com.example.weddingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weddingapp.databinding.ActivityOnskelistaScreenBinding
import com.google.firebase.firestore.*

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

        binding.wishlistAddButton.setOnClickListener {
            val window = PopupWindow(this)
            val view = layoutInflater.inflate(R.layout.add_gift,null)
            window.contentView = view
        }

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

    private fun addData() {
        val gift: MutableMap<String, String> = HashMap()

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