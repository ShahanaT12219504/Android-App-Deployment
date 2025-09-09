package com.example.androidcanvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.graphics.Path
import android.graphics.RectF

class MyCanvasView(context: Context) : View(context) {
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // --- Triangle ---
        paint.color = Color.BLUE
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 6f

        val path = Path()
        path.moveTo(300f, 150f)   // First point (x,y)
        path.lineTo(500f, 450f)   // Second point
        path.lineTo(100f, 450f)   // Third point
        path.close()

        canvas.drawPath(path, paint)

        // --- Smiley Face ---
        val smileyCenterX = 700f
        val smileyCenterY = 300f
        val radius = 120f

        // Face
        paint.color = Color.YELLOW
        paint.style = Paint.Style.FILL
        canvas.drawCircle(smileyCenterX, smileyCenterY, radius, paint)

        // Outline
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 6f
        canvas.drawCircle(smileyCenterX, smileyCenterY, radius, paint)

        // Eyes
        paint.style = Paint.Style.FILL
        canvas.drawCircle(smileyCenterX - 40f, smileyCenterY - 40f, 12f, paint)
        canvas.drawCircle(smileyCenterX + 40f, smileyCenterY - 40f, 12f, paint)

        // Smile
        val smileRect = RectF(
            smileyCenterX - 60f, smileyCenterY - 30f,
            smileyCenterX + 60f, smileyCenterY + 70f
        )
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8f
        canvas.drawArc(smileRect, 20f, 140f, false, paint)

        // --- Android Logo (scaled) ---
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL

        val scale = 0.5f
        val topY = 650f
        val leftX = 200f

        val headWidth = 300f * scale
        val headHeight = 200f * scale
        val bodyWidth = headWidth
        val bodyHeight = 350f * scale

        // Head
        val headRect = RectF(leftX, topY, leftX + headWidth, topY + headHeight)
        canvas.drawArc(headRect, 180f, 180f, true, paint)

        // Body
        val bodyTop = topY + headHeight
        canvas.drawRect(leftX, bodyTop, leftX + bodyWidth, bodyTop + bodyHeight, paint)

        // Legs
        paint.strokeWidth = 10f
        canvas.drawLine(
            leftX + 40f * scale, bodyTop + bodyHeight,
            leftX + 40f * scale, bodyTop + bodyHeight + 50f * scale, paint
        )
        canvas.drawLine(
            leftX + bodyWidth - 40f * scale, bodyTop + bodyHeight,
            leftX + bodyWidth - 40f * scale, bodyTop + bodyHeight + 50f * scale, paint
        )

        // Arms (both downward)
        canvas.drawLine(
            leftX, bodyTop + 30f * scale,
            leftX - 40f * scale, bodyTop + bodyHeight, paint
        )
        canvas.drawLine(
            leftX + bodyWidth, bodyTop + 30f * scale,
            leftX + bodyWidth + 40f * scale, bodyTop + bodyHeight, paint
        )

        // Antennas
        paint.strokeWidth = 8f
        canvas.drawLine(
            leftX + 60f * scale, topY,
            leftX + 40f * scale, topY - 30f * scale, paint
        )
        canvas.drawLine(
            leftX + bodyWidth - 60f * scale, topY,
            leftX + bodyWidth - 40f * scale, topY - 30f * scale, paint
        )

        // Eyes
        paint.color = Color.BLACK
        canvas.drawCircle(leftX + 70f * scale, topY + 70f * scale, 8f * scale, paint)
        canvas.drawCircle(leftX + bodyWidth - 70f * scale, topY + 70f * scale, 8f * scale, paint)

        // --- Text ---
        paint.color = Color.BLACK
        paint.textSize = 45f
        paint.style = Paint.Style.FILL
        canvas.drawText("Hello Android", 100f, 600f, paint)
    }
}
