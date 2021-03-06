package com.example.weddingapp

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.weddingapp.databinding.ActivityQuestionsScreenBinding

class QuestionsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionsScreenBinding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonClicks()
        buttonEffect(binding.ninnaPhoneIcon)
        buttonEffect(binding.ninnaEmailIcon)
        buttonEffect(binding.johanPhoneIcon)
        buttonEffect(binding.johanEmailIcon)
        buttonEffect(binding.toastmaster1PhoneIcon)
        buttonEffect(binding.toastmaster1EmailIcon)
        buttonEffect(binding.toastmaster2PhoneIcon)
        buttonEffect(binding.toastmaster2EmailIcon)
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
        binding.toastmaster1PhoneIcon.setOnClickListener {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("text", binding.toastmaster1PhoneNumber.text))
            Toast.makeText(this, R.string.nummer_kopierat, Toast.LENGTH_LONG).show()
        }
        binding.toastmaster1EmailIcon.setOnClickListener {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("text", binding.toastmaster1Email.text))
            Toast.makeText(this, R.string.nummer_kopierat, Toast.LENGTH_LONG).show()
        }
        binding.toastmaster2PhoneIcon.setOnClickListener {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("text", binding.toastmaster2PhoneNumber.text))
            Toast.makeText(this, R.string.nummer_kopierat, Toast.LENGTH_LONG).show()
        }
        binding.toastmaster2EmailIcon.setOnClickListener {
            clipboardManager.setPrimaryClip(ClipData.newPlainText("text", binding.toastmaster2Email.text))
            Toast.makeText(this, R.string.nummer_kopierat, Toast.LENGTH_LONG).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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