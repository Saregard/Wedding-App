package com.example.weddingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import com.example.weddingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonEffect(binding.boendeButton)
        buttonEffect(binding.bordsplaceringButton)
        buttonEffect(binding.kladkodButton)
        buttonEffect(binding.korschemaButton)
        buttonEffect(binding.onskelistaButton)
        buttonEffect(binding.platsButton)
        buttonEffect(binding.questionsButton)

        binding.boendeButton.setOnClickListener {
            val singUpIntent = Intent(this, BoendeScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.bordsplaceringButton.setOnClickListener {
            val singUpIntent = Intent(this, BordsplaceringScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.kladkodButton.setOnClickListener {
            val singUpIntent = Intent(this, KladkodScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.korschemaButton.setOnClickListener {
            val singUpIntent = Intent(this, KorschemaScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.onskelistaButton.setOnClickListener {
            val singUpIntent = Intent(this, OnskelistaScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.platsButton.setOnClickListener {
            val singUpIntent = Intent(this, PlatsScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

        binding.questionsButton.setOnClickListener {
            val singUpIntent = Intent(this, QuestionsScreen::class.java)
            startActivity(singUpIntent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

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