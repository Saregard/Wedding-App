package com.example.weddingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.weddingapp.databinding.ActivityBordsplaceringScreenBinding

class BordsplaceringScreen : AppCompatActivity() {

    private lateinit var binding: ActivityBordsplaceringScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBordsplaceringScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonMap: Map<Class<*>, ImageButton> = mapOf(
            Pair(BoendeScreen::class.java, binding.bord1),
            Pair(BoendeScreen::class.java, binding.bord2),
            Pair(BoendeScreen::class.java, binding.bord3),
            Pair(BoendeScreen::class.java, binding.bord4),
            Pair(BoendeScreen::class.java, binding.bord5),
            Pair(BoendeScreen::class.java, binding.bord6),
            Pair(BoendeScreen::class.java, binding.bord7),
            Pair(BoendeScreen::class.java, binding.bord8),
            Pair(BoendeScreen::class.java, binding.bord9),
            Pair(BoendeScreen::class.java, binding.bord10)
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