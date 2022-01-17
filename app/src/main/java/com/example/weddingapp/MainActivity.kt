package com.example.weddingapp

import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.weddingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getViews(binding.boendeText, getString(R.string.BOENDE))
        getViews(binding.bordsplaceringText, getString(R.string.BORDSPLACERING))
        getViews(binding.kladkodText, getString(R.string.KLÄDKOD))
        getViews(binding.korschemaText, getString(R.string.KÖRSCHEMA))
        getViews(binding.onskelistaText, getString(R.string.ÖNSKELISTA))
        getViews(binding.platsText, getString(R.string.PLATS))

        val imageViewList = listOf(
            binding.boendeText,
            binding.bordsplaceringText,
            binding.kladkodText,
            binding.korschemaText,
            binding.onskelistaText,
            binding.platsText
        )

        for (imageView in imageViewList) {
            imageView.rotation = 90f
        }

        val buttonMap: Map<Class<*>, ImageButton> = mapOf(
            Pair(DataInput::class.java, binding.boendeButton),
            Pair(BordsplaceringScreen::class.java, binding.bordsplaceringButton),
            Pair(KladkodScreen::class.java, binding.kladkodButton),
            Pair(KorschemaScreen::class.java, binding.korschemaButton),
            Pair(WishlistScreen::class.java, binding.onskelistaButton),
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

    private fun getViews(imageView: ImageView, text: String) {
        imageView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                imageView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                imageView.setImageBitmap(
                    CurvedTextView().drawTextOnPath(
                        this@MainActivity,
                        imageView.width,
                        imageView.height,
                        text
                    )
                )
            }
        })
    }
}