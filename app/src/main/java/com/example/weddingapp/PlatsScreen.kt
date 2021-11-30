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
        setContentView(R.layout.activity_plats_screen)

        binding = ActivityPlatsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonMap: Map<Class<*>, ImageButton> = mapOf(
            Pair(LokalScreen::class.java, binding.lokalButton),
            Pair(KyrkaScreen::class.java, binding.kyrkaButton),
        )

        for (button in buttonMap) {
            val anim1 = R.anim.slide_in_right
            val anim2 = R.anim.slide_out_left
            ButtonEffect.effect(button.value)
            Slider.slider(button.value, this, this, button.key, anim1, anim2)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}