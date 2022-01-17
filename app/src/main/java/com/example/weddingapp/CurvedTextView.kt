package com.example.weddingapp

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import androidx.core.content.res.ResourcesCompat
import kotlin.math.min

class CurvedTextView {


    fun drawTextOnPath(context: Context, width: Int, height: Int, text: String): Bitmap? {
        val bitmap = Bitmap.createBitmap(
            width,
            height,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)

        val path = Path()

        path.addCircle(width / 2F, height / 2F, min(width, height) / 2.5F, Path.Direction.CW)

        val textPaint = TextPaint().apply {
            isAntiAlias = true
            color = Color.parseColor("#000000")
            textSize = 50F
            typeface = ResourcesCompat.getFont(context, R.font.brawler)
            textAlign = Paint.Align.CENTER
        }

        canvas.drawTextOnPath(text, path, 0f, 0F, textPaint)

        return bitmap
    }

}