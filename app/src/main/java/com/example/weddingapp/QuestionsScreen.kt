package com.example.weddingapp

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.Toast
import com.example.weddingapp.databinding.ActivityQuestionsScreenBinding

class QuestionsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonClicks()
        buttonEffect(binding.ninnaPhoneIcon)
        buttonEffect(binding.ninnaEmailIcon)
        buttonEffect(binding.johanPhoneIcon)
        buttonEffect(binding.johanEmailIcon)

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun buttonClicks () {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        binding.ninnaPhoneIcon.setOnClickListener {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("text", binding.ninnaPhoneNumber.text))
            Toast.makeText(this, R.string.nummer_kopierat, Toast.LENGTH_LONG).show()
        }
        binding.ninnaEmailIcon.setOnClickListener {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("text", binding.ninnaEmail.text))
            Toast.makeText(this, R.string.email_kopierad, Toast.LENGTH_LONG).show()
        }
        binding.johanPhoneIcon.setOnClickListener {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("text", binding.johanPhoneNumber.text))
            Toast.makeText(this, R.string.nummer_kopierat, Toast.LENGTH_LONG).show()
        }
        binding.johanEmailIcon.setOnClickListener {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("text", binding.johanEmail.text))
            Toast.makeText(this, R.string.email_kopierad, Toast.LENGTH_LONG).show()
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