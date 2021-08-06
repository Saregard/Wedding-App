package com.example.weddingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weddingapp.databinding.ActivityBordsplaceringScreenBinding
import com.example.weddingapp.databinding.ActivityMainBinding

class BordsplaceringScreen : AppCompatActivity() {

    private lateinit var binding: ActivityBordsplaceringScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBordsplaceringScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}