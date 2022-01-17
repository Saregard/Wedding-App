package com.example.weddingapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import com.example.weddingapp.databinding.ActivityPlatsScreenBinding

class PlatsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityPlatsScreenBinding

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlatsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Slider.slider(
            binding.lokalButton, this, this, LokalScreen::class.java,
            R.anim.slide_in_up, R.anim.slide_out_down
        )
        Slider.slider(
            binding.kyrkaButton, this, this, KyrkaScreen::class.java,
            R.anim.slide_in_down, R.anim.slide_out_up
        )
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}