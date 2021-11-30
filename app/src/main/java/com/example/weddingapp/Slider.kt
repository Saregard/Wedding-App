package com.example.weddingapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageButton
import android.R


object Slider {

    fun  slider(
        button: ImageButton,
        context: Context,
        activity: Activity,
        klass: Class<*>?,
        animationIn: Int,
        animationOut: Int
    ) {
        button.setOnClickListener {
            val singUpIntent = Intent(activity, klass)
            context.startActivity(singUpIntent)
            (context as Activity).overridePendingTransition(animationIn, animationOut)
        }
    }
}
