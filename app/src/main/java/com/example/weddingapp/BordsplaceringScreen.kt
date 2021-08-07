package com.example.weddingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import com.example.weddingapp.databinding.ActivityBordsplaceringScreenBinding
import com.example.weddingapp.databinding.ActivityMainBinding

class BordsplaceringScreen : AppCompatActivity() {

    private lateinit var binding: ActivityBordsplaceringScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBordsplaceringScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonEffect(binding.bord1)
        buttonEffect(binding.bord2)
        buttonEffect(binding.bord3)
        buttonEffect(binding.bord4)
        buttonEffect(binding.bord5)
        buttonEffect(binding.bord6)
        buttonEffect(binding.bord7)
        buttonEffect(binding.bord8)
        buttonEffect(binding.bord9)
        buttonEffect(binding.bord10)

        binding.bord1.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.bord2.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.bord3.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.bord4.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.bord5.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.bord6.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.bord7.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.bord8.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.bord9.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.bord10.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
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