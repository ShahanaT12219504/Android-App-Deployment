package com.example.androidcanvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import kotlin.math.*

class RhombusView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.FILL }
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.STROKE }
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = dpToPx(2f)
        color = Color.WHITE
    }
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    private var strokeWidthPx = dpToPx(4f)
    private var insetPx = dpToPx(0f)
    private var fillColor = Color.parseColor("#2196F3")
    private var strokeColor = Color.WHITE

    init {
        attrs?.let {
            val a = context.theme.obtainStyledAttributes(it, R.styleable.RhombusView, 0, 0)
            try {
                fillColor = a.getColor(R.styleable.RhombusView_rhombusFillColor, fillColor)
                strokeColor = a.getColor(R.styleable.RhombusView_rhombusStrokeColor, strokeColor)
                strokeWidthPx = a.getDimension(R.styleable.RhombusView_rhombusStrokeWidth, strokeWidthPx)
                insetPx = a.getDimension(R.styleable.RhombusView_rhombusInset, insetPx)
            } finally { a.recycle() }
        }
        fillPaint.color = fillColor
        strokePaint.color = strokeColor
        strokePaint.strokeWidth = strokeWidthPx
        linePaint.color = strokeColor
        circlePaint.color = strokeColor
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val h = height.toFloat()
        val size = min(w, h)
        val cx = w / 2f
        val cy = h / 2f
        val halfW = (size / 2f) - insetPx
        val halfH = (size / 2f) - insetPx

        val path = Path().apply {
            moveTo(cx, cy - halfH)
            lineTo(cx + halfW, cy)
            lineTo(cx, cy + halfH)
            lineTo(cx - halfW, cy)
            close()
        }

        canvas.drawPath(path, fillPaint)
        if (strokePaint.strokeWidth > 0f) canvas.drawPath(path, strokePaint)

        canvas.drawLine(cx, cy - halfH, cx, cy + halfH, linePaint)
        canvas.drawLine(cx - halfW, cy, cx + halfW, cy, linePaint)

        val extensionLength = min(halfW, halfH) * 1.0f
        val endX = cx + extensionLength
        val endY = cy + extensionLength
        canvas.drawLine(cx, cy, endX, endY, linePaint)

        val circleRadius = dpToPx(14f)
        canvas.drawCircle(endX, endY, circleRadius, circlePaint)

        val triHeight = dpToPx(24f)
        val triHalfWidth = dpToPx(14f)
        val triPath = Path().apply {
            moveTo(cx, cy + halfH + triHeight) // bottom apex
            lineTo(cx - triHalfWidth, cy + halfH)
            lineTo(cx + triHalfWidth, cy + halfH)
            close()
        }
        canvas.drawPath(triPath, fillPaint)
        canvas.drawPath(triPath, strokePaint)

        val footLen = dpToPx(20f)
        val footAngle = 30f
        val sidePoints = arrayOf( // only left & right sides
            Pair(cx + halfW, cy),
            Pair(cx - halfW, cy)
        )
        for ((px, py) in sidePoints) {
            for (i in -1..1) {
                val angle = if (px == cx + halfW) 0f + i * footAngle else 180f + i * footAngle
                val rad = Math.toRadians(angle.toDouble())
                val fx = px + cos(rad) * footLen
                val fy = py + sin(rad) * footLen
                canvas.drawLine(px, py, fx.toFloat(), fy.toFloat(), linePaint)
            }
        }
    }

    private fun dpToPx(dp: Float): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

    fun setFillColor(color: Int) {
        fillColor = color; fillPaint.color = color; invalidate()
    }

    fun setStrokeColor(color: Int) {
        strokeColor = color
        strokePaint.color = color
        linePaint.color = color
        circlePaint.color = color
        invalidate()
    }

    fun setStrokeWidthDp(dp: Float) {
        strokeWidthPx = dpToPx(dp); strokePaint.strokeWidth = strokeWidthPx; invalidate()
    }

    fun setInsetDp(dp: Float) {
        insetPx = dpToPx(dp); invalidate()
    }
}
