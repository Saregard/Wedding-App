package com.example.weddingapp

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.weddingapp.databinding.ActivityGiftAddPopupWindowBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2
import java.util.*


class GiftAddPopupWindow : AppCompatActivity() {

    private lateinit var binding: ActivityGiftAddPopupWindowBinding
    private val db = FirebaseFirestore.getInstance()
    private var selectedPhotoUri: Uri? = null
    private var finalDownloadedImage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGiftAddPopupWindowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        extraTextExpandEditText()
        selectPicture()
        addButton()
    }

    private fun addButton() {
        binding.saveGiftButton.setOnClickListener {
            if (binding.giftTitle.editText?.text?.isEmpty() == true) {
                binding.giftTitle.error = "Fyll i en titel"
            } else

                finalDownloadedImage?.let { websiteUrl ->
                    addData(
                        giftTitle = binding.giftTitle.editText?.text.toString(),
                        giftPrice = binding.giftPrice.editText?.text.toString() + " SEK",
                        giftWebsite = binding.giftWebsite.editText?.text.toString(),
                        giftExtraText = binding.giftExtraText.editText?.text.toString(),
                        websiteUrl
                    )
                    finish()
                }
        }
        binding.cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun selectPicture() {
        val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) {
            val picture = binding.addPicture
            picture.scaleType = ImageView.ScaleType.CENTER_CROP
            selectedPhotoUri = it
            picture.setImageURI(it)
            uploadImage()
        }
        binding.addPicture.setOnClickListener {
            getImage.launch("image/*")
        }
    }

    @SuppressLint("Recycle")
    private fun uploadImage() {
        val progressBar: ProgressBar = findViewById(R.id.progressBar_horizontal)

        if (selectedPhotoUri == null) return

        val fileName = UUID.randomUUID().toString()
        val reference = FirebaseStorage.getInstance().getReference("/images/$fileName")

        reference.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                progressBar.progress = 0
                reference.downloadUrl.addOnSuccessListener { downloadedImage ->
                    val di = downloadedImage.toString()
                    Log.d(TAG, "image url: $downloadedImage")
                    finalDownloadedImage = di
                    progressBar.postDelayed({ progressBar.visibility = View.INVISIBLE }, 1000)
                }
            }
            .addOnProgressListener { (bytesTransferred, totalByteCount) ->
                progressBar.visibility = View.VISIBLE
                progressBar.max = 1000
                val progress = (1000 * bytesTransferred) / totalByteCount
                ObjectAnimator.ofInt(progressBar, "progress", progress.toInt())
                    .setDuration(progress)
                    .start()
                progressBar.progress = progress.toInt()
            }
    }

    private fun addData(
        giftTitle: String,
        giftPrice: String,
        giftWebsite: String,
        giftExtraText: String,
        firestoreImageUrl: String
    ) {
        val gift: MutableMap<String, String> = HashMap()

        gift["giftTitle"] = giftTitle
        gift["giftPrice"] = giftPrice
        gift["giftWebsite"] = giftWebsite
        gift["giftExtraText"] = giftExtraText
        gift["firestoreImageUrl"] = firestoreImageUrl


        db.collection("wishlist").document(giftTitle)
            .set(gift)
            .addOnSuccessListener {
                binding.saveGiftButton.setOnClickListener {
                    finish()
                }
                Toast.makeText(this, "This was a success", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "This was a failure", Toast.LENGTH_SHORT).show()
            }
    }

    private fun extraTextExpandEditText() {
        binding.giftExtraText.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.giftExtraText.editText?.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
                binding.giftExtraText.editText?.setLines(2)
            } else {
                binding.giftExtraText.editText?.inputType = InputType.TYPE_CLASS_TEXT
                binding.giftExtraText.editText?.setLines(1)
            }
        }
    }
}