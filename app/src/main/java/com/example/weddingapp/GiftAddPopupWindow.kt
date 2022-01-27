package com.example.weddingapp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.weddingapp.databinding.ActivityGiftAddPopupWindowBinding
import com.example.weddingapp.databinding.ActivityOnskelistaScreenBinding
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*
import android.view.WindowManager


class GiftAddPopupWindow : AppCompatActivity() {

    private lateinit var binding: ActivityGiftAddPopupWindowBinding
    private val db = FirebaseFirestore.getInstance()
    lateinit var imageUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGiftAddPopupWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectPicture()
        addButton()
//        setWindowParams()

    }

    private fun setWindowParams() {
        val popupWindow = window.attributes
        popupWindow.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS

        window.attributes = popupWindow
    }

    private fun addButton() {

        binding.saveGiftButton.setOnClickListener {
            if (binding.giftTitle.editText?.text?.isEmpty() == true) {
                binding.giftTitle.error = "Fyll i en titel"
            } else
                addData(
                    binding.giftTitle.editText?.text.toString(),
                    binding.giftPrice.editText?.text.toString() + " SEK",
                    binding.giftWebsite.editText?.text.toString()
                )
            uploadImage()

        }
        binding.cancelButton.setOnClickListener {

        }
    }

    private fun goBack() {
        val intent = Intent(this, WishlistScreen::class.java)
        startActivity(intent)
    }

    private fun uploadImage() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(imageUri).addOnSuccessListener {
            binding.firebaseImage.setImageURI(null)
            Toast.makeText(this, "Successfully uploaded", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Successfully uploaded", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun selectPicture() {
        val getImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.firebaseImage.setImageURI(it)
            }
        )

        binding.addPicture.setOnClickListener {
            getImage.launch("image/*")
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
}