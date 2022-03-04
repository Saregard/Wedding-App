package com.example.weddingapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weddingapp.databinding.ActivityLokalScreenBinding
import com.example.weddingapp.databinding.ActivityMainBinding

class LokalScreen : AppCompatActivity() {

    private lateinit var binding: ActivityLokalScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLokalScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.adressBild.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data =
                Uri.parse("https://www.google.com/maps/place/Annersag%C3%A5rd/@57.1898297,12.2598412,12.75z/data=!4m5!3m4!1s0x46502999be385657:0xe5deab0789c57811!8m2!3d57.190615!4d12.2804674")
            startActivity(openURL)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_up)
    }
}