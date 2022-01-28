package com.example.weddingapp

import android.content.ContentValues.TAG
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.example.weddingapp.databinding.ActivityGiftAddPopupWindowBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.*

class GiftAddPopupWindow : AppCompatActivity() {

    private lateinit var binding: ActivityGiftAddPopupWindowBinding
    private val db = FirebaseFirestore.getInstance()
    private var selectedPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGiftAddPopupWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectPicture()
        addButton()
    }

    private fun addButton() {

        binding.saveGiftButton.setOnClickListener {
            if (binding.giftTitle.editText?.text?.isEmpty() == true) {
                binding.giftTitle.error = "Fyll i en titel"
            } else

                finalDownloadedImage?.let { it1 ->
                    addData(
                        binding.giftTitle.editText?.text.toString(),
                        binding.giftPrice.editText?.text.toString() + " SEK",
                        binding.giftWebsite.editText?.text.toString(),
                        it1

                    )
                }
            uploadImage()

        }
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    var finalDownloadedImage : String? = null

    private fun uploadImage() {

        if (selectedPhotoUri == null) return

        val fileName = UUID.randomUUID().toString()
        val reference = FirebaseStorage.getInstance().getReference("/images/$fileName")

        reference.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("RegisterASD", "uploadImage: ${it.metadata?.path}")

                reference.downloadUrl.addOnSuccessListener { downloadedImage ->
                    val di = downloadedImage.toString()
                    Log.d(TAG, "image url: $downloadedImage")
                    finalDownloadedImage = di
                }
            }
    }


    private fun selectPicture() {
        val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            val picture = binding.addPicture
            picture.scaleType = ImageView.ScaleType.CENTER_CROP
            selectedPhotoUri = it
            picture.setImageURI(it)

        }
        binding.addPicture.setOnClickListener {
            getImage.launch("image/*")
        }
    }

    private fun addData(
        giftTitle: String,
        giftPrice: String,
        giftWebsite: String,
        firestoreImageUrl: String
    ) {
        val gift: MutableMap<String, String> = HashMap()

        gift["giftTitle"] = giftTitle
        gift["giftPrice"] = giftPrice
        gift["giftWebsite"] = giftWebsite
        gift["firestoreImageUrl"] = firestoreImageUrl

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