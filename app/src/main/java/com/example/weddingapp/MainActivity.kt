package com.example.weddingapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.weddingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonMap: Map<Class<*>, ImageButton> = mapOf(
            Pair(DataInput::class.java, binding.boendeButton),
            Pair(BordsplaceringScreen::class.java, binding.bordsplaceringButton),
            Pair(KladkodScreen::class.java, binding.kladkodButton),
            Pair(KorschemaScreen::class.java, binding.korschemaButton),
            Pair(OnskelistaScreen::class.java, binding.onskelistaButton),
            Pair(PlatsScreen::class.java, binding.platsButton),
            Pair(QuestionsScreen::class.java, binding.questionsButton)
        )

        for (button in buttonMap) {
            val anim1 = R.anim.slide_in_right
            val anim2 = R.anim.slide_out_left
            ButtonEffect.effect(button.value)
            Slider.slider(button.value, this, this, button.key, anim1, anim2)
        }
    }
}