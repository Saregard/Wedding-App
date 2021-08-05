package com.example.weddingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import com.example.weddingapp.databinding.ActivityMainBinding
import com.example.weddingapp.databinding.ActivityPlatsScreenBinding

class PlatsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityPlatsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plats_screen)

        binding = ActivityPlatsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonEffect(binding.kyrkaButton)
        buttonEffect(binding.lokalButton)

        binding.kyrkaButton.setOnClickListener {
            val singUpIntent = Intent(this, KyrkaScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.lokalButton.setOnClickListener {
            val singUpIntent = Intent(this, LokalScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    fun buttonEffect(button: ImageButton) {
        button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleY(1.1f).duration = 50
                    v.animate().scaleX(1.1f).duration = 50
                    v.invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    v.animate().scaleY(1.0f).duration = 50
                    v.animate().scaleX(1.0f).duration = 50
                    v.background.clearColorFilter()
                    v.invalidate()
                }
            }
            false
        }
    }
}