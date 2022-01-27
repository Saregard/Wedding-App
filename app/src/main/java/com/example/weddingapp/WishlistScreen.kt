package com.example.weddingapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weddingapp.databinding.ActivityOnskelistaScreenBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.*
import android.net.Uri
import android.util.AttributeSet
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.EventListener
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


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

        eventChangeListener()
        openButton()
    }

    private fun openButton() {
        binding.wishlistAddButton.setOnClickListener {
            val intent = Intent(this, GiftAddPopupWindow::class.java)
            startActivity(intent)

        }
    }

    private fun eventChangeListener() {
        db.collection("wishlist")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                @SuppressLint("NotifyDataSetChanged")
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

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}