package com.example.weddingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

//        swipe()
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

//    private fun swipe(){
//        val swipeGesture = object : SwipeGesture(this){
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                when (direction){
//                    ItemTouchHelper.LEFT -> {
//                        myAdapter.deleteItem(viewHolder.absoluteAdapterPosition,)
//                    }
//                }
//            }
//        }
//        val touchHelper = ItemTouchHelper(swipeGesture)
//        touchHelper.attachToRecyclerView(recyclerView)
//    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}