package com.example.weddingapp

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.weddingapp.databinding.ActivityPlatsScreenBinding
import com.google.android.material.card.MaterialCardView

class PlatsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityPlatsScreenBinding

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlatsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getViews(binding.lokalText, "FEST LOKAL")
        getViews(binding.kyrkaText, "KYRKA")

        val imageViewList = listOf(
            binding.lokalText,
            binding.kyrkaText
        )

        for (imageView in imageViewList) {
            imageView.rotation = 90f
        }

        Slider.slider2(
            binding.lokalButton, this, this, LokalScreen::class.java,
            R.anim.slide_in_up, R.anim.slide_out_down
        )
        Slider.slider2(
            binding.kyrkaButton, this, this, KyrkaScreen::class.java,
            R.anim.slide_in_down, R.anim.slide_out_up
        )
    }

    private fun getViews(imageView: ImageView, text: String) {
        imageView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                imageView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                imageView.setImageBitmap(
                    CurvedTextView().drawTextOnPath(
                        this@PlatsScreen,
                        imageView.width,
                        imageView.height,
                        text,
                        80f
                    )
                )
            }
        })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}