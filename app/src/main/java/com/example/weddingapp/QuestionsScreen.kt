package com.example.weddingapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weddingapp.databinding.ActivityMainBinding
import com.example.weddingapp.databinding.ActivityQuestionsScreenBinding

class QuestionsScreen : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuestionsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ninnaEmailIcon.setOnClickListener {
            copyTextToClipboard()
        }

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun copyTextToClipboard() {
        val textToCopy = binding.ninnaEmai.text
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }

}