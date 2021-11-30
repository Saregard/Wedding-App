package com.example.weddingapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weddingapp.databinding.ActivityDataInputBinding
import com.example.weddingapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class DataInput : AppCompatActivity() {



    private lateinit var binding: ActivityDataInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("UserInfo").document("oskar")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("exist", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("noexist", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("errordb", "get failed with ", exception)
            }

        binding.fRnamn.hint = "Förnamn"
        binding.efternamn.hint= "Efternamn"
        binding.allergi.hint = "Allergi"

        binding.fRnamn.text

    }
}