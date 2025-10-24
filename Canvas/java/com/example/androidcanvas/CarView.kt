package com.example.androidcanvas

import android.R.attr.width
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 8f
    }
    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()

        val baseY = h * 0.7f
        val carHeight = h * 0.25f

        val bodyPath = Path().apply {
            moveTo(w * 0.1f, baseY) // Start: front bottom
            lineTo(w * 0.1f, baseY - carHeight * 0.2f) // Front vertical
            quadTo(w * 0.15f, baseY - carHeight * 0.5f, w * 0.25f, baseY - carHeight * 0.6f) // Headlight curve
            lineTo(w * 0.88f, baseY - carHeight * 0.6f) // Top line of the car body
            quadTo(w * 0.95f, baseY - carHeight * 0.5f, w * 0.9f, baseY - carHeight * 0.1f) // Back curve
            lineTo(w * 0.9f, baseY) // Back vertical
            lineTo(w * 0.1f, baseY) // Bottom line of the car body
        }
        canvas.drawPath(bodyPath, strokePaint)

        val roofPath = Path().apply {
            moveTo(w * 0.2f, baseY - carHeight * 0.6f) // Start of roof from windshield
            quadTo(w * 0.45f, baseY - carHeight * 1.1f, w * 0.6f, baseY - carHeight * 0.9f) // Top of roof
            lineTo(w * 0.85f, baseY - carHeight * 0.6f) // End of roof at back window
            lineTo(w * 0.2f, baseY - carHeight * 0.6f) // Close path
        }
        canvas.drawPath(roofPath, strokePaint)

        // Window divider
        canvas.drawLine(
            w * 0.5f, baseY - carHeight * 0.9f,
            w * 0.5f, baseY - carHeight * 0.6f,
            strokePaint
        )

        val wheelRadius = carHeight * 0.3f
        val wheelY = baseY - wheelRadius * 0.1f
        val wheelOffset = w * 0.15f

        canvas.drawCircle(w * 0.25f + wheelOffset, wheelY, wheelRadius, strokePaint)

        canvas.drawCircle(w * 0.7f + wheelOffset, wheelY, wheelRadius, strokePaint)

        val wheelWellPathFront = Path().apply {
            moveTo(w * 0.25f, baseY)
            quadTo(w * 0.35f, baseY - wheelRadius * 1.5f, w * 0.4f, baseY)
        }
        canvas.drawPath(wheelWellPathFront, strokePaint)

        val wheelWellPathRear = Path().apply {
            moveTo(w * 0.68f, baseY)
            quadTo(w * 0.78f, baseY - wheelRadius * 1.5f, w * 0.83f, baseY)
        }
        canvas.drawPath(wheelWellPathRear, strokePaint)
    }
}