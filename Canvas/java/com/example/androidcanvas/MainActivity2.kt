package com.example.androidcanvas

import android.graphics.*
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instead of XML layout, set a custom view directly:
        setContentView(object : View(this) {

            // Bat paints
            private val bladePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#DEB887") // light wood
                style = Paint.Style.FILL
            }
            private val bladeEdgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.BLACK
                style = Paint.Style.STROKE
                strokeWidth = 6f
            }
            private val handlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                shader = LinearGradient(
                    0f, 0f, 0f, 200f,
                    Color.RED, Color.DKGRAY,
                    Shader.TileMode.CLAMP
                )
                style = Paint.Style.FILL
            }

            // Circle + square paints
            private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#1E90FF")
                style = Paint.Style.STROKE
                strokeWidth = 5f
            }
            private val squarePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.parseColor("#008000")
                style = Paint.Style.STROKE
                strokeWidth = 5f
            }

            override fun onDraw(canvas: Canvas) {
                super.onDraw(canvas)

                val w = width.toFloat()
                val h = height.toFloat()

                // -------- cricket bat --------
                canvas.save()
                canvas.rotate(-15f, w / 2, h / 2)

                val handleWidth = w * 0.1f
                val handleHeight = h * 0.25f
                val handleLeft = w / 2 - handleWidth / 2
                val handleTop = h * 0.1f
                val handleRect = RectF(
                    handleLeft,
                    handleTop,
                    handleLeft + handleWidth,
                    handleTop + handleHeight
                )
                canvas.drawRect(handleRect, handlePaint)
                canvas.drawRect(handleRect, bladeEdgePaint)

                val bladeLeft = w / 2 - w * 0.15f
                val bladeTop = handleTop + handleHeight
                val bladeRight = w / 2 + w * 0.15f
                val bladeBottom = h * 0.9f
                val bladeRect = RectF(bladeLeft, bladeTop, bladeRight, bladeBottom)

                canvas.drawRoundRect(bladeRect, 30f, 30f, bladePaint)
                canvas.drawRoundRect(bladeRect, 30f, 30f, bladeEdgePaint)

                canvas.restore()
                // -------- end bat --------

                // -------- circle + square --------
                // put this on top-left area so it doesn’t overlap bat
                val cx = w * 0.2f
                val cy = h * 0.2f
                val radius = w * 0.12f

                // Circle
                canvas.drawCircle(cx, cy, radius, circlePaint)

                // Square with corners touching circle
                val halfSide = (radius / Math.sqrt(2.0)).toFloat()
                val rect = RectF(
                    cx - halfSide,
                    cy - halfSide,
                    cx + halfSide,
                    cy + halfSide
                )

                canvas.save()
                canvas.rotate(45f, cx, cy)
                canvas.drawRect(rect, squarePaint)
                canvas.restore()
                // -------- end circle + square --------
            }
        })
    }
}
