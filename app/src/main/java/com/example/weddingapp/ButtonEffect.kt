package com.example.weddingapp

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.ImageButton
import com.google.android.material.card.MaterialCardView

object ButtonEffect {

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    fun effect(button: ImageButton) {
        button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleY(1.05f).duration = 80
                    v.animate().scaleX(1.05f).duration = 80
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

    @SuppressLint("ClickableViewAccessibility")
    fun effect2(button: MaterialCardView) {
        button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.animate().scaleY(1.05f).duration = 80
                    v.animate().scaleX(1.05f).duration = 80
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