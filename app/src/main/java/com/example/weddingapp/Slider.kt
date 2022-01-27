package com.example.weddingapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.ImageButton
import com.google.android.material.card.MaterialCardView


object Slider {

    fun  slider1(
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

    fun  slider2(
        button: MaterialCardView,
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
