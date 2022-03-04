package com.example.weddingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weddingapp.databinding.ActivityDataInputBinding
import com.google.firebase.firestore.FirebaseFirestore

class DataInput : AppCompatActivity() {

    private lateinit var binding: ActivityDataInputBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.firstName.hint = "Förnamn"
        binding.lastName.hint = "Efternamn"
        binding.allergi.hint = "Allergi"

        binding.comingButton.setOnClickListener {
            if (binding.firstName.editText.toString().isNotEmpty() && binding.lastName.editText?.text.toString().isNotEmpty()) {
                addData(
                    binding.firstName.editText?.text.toString(),
                    binding.lastName.editText?.text.toString(),
                    binding.allergi.editText?.text.toString()
                )
            }
            if (binding.firstName.editText?.text?.isEmpty() == true) {
                binding.firstName.error = "Fyll i förnamn"
            }
            if (binding.lastName.editText?.text?.isEmpty() == true) {
                binding.lastName.error = "Fyll i efternamn"
            }
        }
    }

    private fun addData(firstName: String, lastName: String, allergi: String) {

        val user: MutableMap<String, String> = HashMap()
        user["firstName"] = firstName
        user["lastName"] = lastName
        user["allergi"] = allergi

        db.collection("userInfo")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "This was a success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "This was a failure", Toast.LENGTH_SHORT).show()
            }
    }

    private fun readData() {
        db.collection("userInfo")
            .get()
            .addOnCompleteListener {
                val result = StringBuffer()
                if (it.isSuccessful) {
                    for (document in it.result!!) {
                        result.append(document.data.getValue("firstName")).append(" ")
                            .append(document.data.getValue("lastName")).append(" ")
                            .append(document.data.getValue("allergi")).append("\n\n")
                    }
                    binding.comingButton.text = result
                }
            }
            .addOnFailureListener {
            }
    }
}