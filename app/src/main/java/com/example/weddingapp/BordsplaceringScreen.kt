package com.example.weddingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BordsplaceringScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bordsplacering_screen)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}