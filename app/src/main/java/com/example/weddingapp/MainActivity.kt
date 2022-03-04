package com.example.weddingapp

import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.weddingapp.databinding.ActivityMainBinding
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonMapping()
        curvedTextViewRotations()

        getViewsForCurvedText(binding.boendeText, getString(R.string.BOENDE))
        getViewsForCurvedText(binding.bordsplaceringText, getString(R.string.BORDSPLACERING))
        getViewsForCurvedText(binding.kladkodText, getString(R.string.KLÄDKOD))
        getViewsForCurvedText(binding.tidsschemaText, getString(R.string.TIDSSHCEMA))
        getViewsForCurvedText(binding.onskelistaText, getString(R.string.ÖNSKELISTA))
        getViewsForCurvedText(binding.platsText, getString(R.string.PLATS))
    }

    private fun curvedTextViewRotations() {
        val imageViewList = listOf(
            binding.boendeText,
            binding.bordsplaceringText,
            binding.kladkodText,
            binding.tidsschemaText,
            binding.onskelistaText,
            binding.platsText
        )

        for (imageView in imageViewList) {
            imageView.rotation = 90f
        }
    }

    private fun getViewsForCurvedText(imageView: ImageView, text: String) {
        imageView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                imageView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                imageView.setImageBitmap(
                    CurvedTextView().drawTextOnPath(
                        this@MainActivity,
                        imageView.width,
                        imageView.height,
                        text, 50f
                    )
                )
            }
        })
    }

    private fun buttonMapping() {
        val buttonMap: Map<Class<*>, MaterialCardView> = mapOf(
            Pair(DataInput::class.java, binding.boendeButton),
            Pair(BordsplaceringScreen::class.java, binding.bordsplaceringButton),
            Pair(KladkodScreen::class.java, binding.kladkodButton),
            Pair(KorschemaScreen::class.java, binding.tidsschemaButton),
            Pair(WishlistScreen::class.java, binding.wishlistButton),
            Pair(PlatsScreen::class.java, binding.platsButton),
            Pair(QuestionsScreen::class.java, binding.questionsButton)
        )

        for (button in buttonMap) {
            val anim1 = R.anim.slide_in_right
            val anim2 = R.anim.slide_out_left
            ButtonEffect.effect2(button.value)
            Slider.slider2(button.value, this, this, button.key, anim1, anim2)
        }
    }
}