package com.example.androidcanvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CanvasAPIView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()

        // Background (white board area)
        paint.color = Color.WHITE
        canvas.drawRect(50f, 100f, width - 50f, height - 100f, paint)

        // Title Text
        paint.color = Color.DKGRAY
        paint.textSize = 60f
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText(
            "Canvas API in Android",
            width / 2,
            height / 3,
            paint
        )

        // Android green base rectangle (bottom)
        paint.color = Color.parseColor("#3DDC84")
        val baseHeight = height * 0.15f
        canvas.drawRect(50f, height - 100f - baseHeight, width - 50f, height - 100f, paint)

        // Android head (semicircle)
        val centerX = width / 2
        val radius = 200f
        val headTop = height - 100f - baseHeight
        val rectF = RectF(centerX - radius, headTop - radius, centerX + radius, headTop + radius)
        canvas.drawArc(rectF, 180f, 180f, true, paint)

        // Eyes
        paint.color = Color.WHITE
        canvas.drawCircle(centerX - 80f, headTop - 65f, 15f, paint)
        canvas.drawCircle(centerX + 80f, headTop - 65f, 15f, paint)

        // Antennae
        paint.color = Color.parseColor("#3DDC84")
        paint.strokeWidth = 12f
        canvas.drawLine(centerX - 120f, headTop - 220f, centerX - 60f, headTop - 70f, paint)
        canvas.drawLine(centerX + 120f, headTop - 220f, centerX + 60f, headTop - 70f, paint)

    }
}
