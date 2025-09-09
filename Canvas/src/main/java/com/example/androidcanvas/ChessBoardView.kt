package com.example.androidcanvas

import android.content.Context
import android.graphics.*
import android.view.View

class ChessBoardView(context: Context) : View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val fullWidth = width.toFloat()
        val fullHeight = height.toFloat()

        // --- Draw chessboard at TOP ---
        val boardSize = fullWidth.coerceAtMost(fullHeight / 3f) * 0.9f
        val squareSize = boardSize / 8f

        val boardStartX = fullWidth / 2f - boardSize / 2f
        val boardStartY = 40f

        for (row in 0 until 8) {
            for (col in 0 until 8) {
                paint.color = if ((row + col) % 2 == 0) Color.WHITE else Color.BLACK
                paint.style = Paint.Style.FILL
                canvas.drawRect(
                    boardStartX + col * squareSize,
                    boardStartY + row * squareSize,
                    boardStartX + (col + 1) * squareSize,
                    boardStartY + (row + 1) * squareSize,
                    paint
                )
            }
        }

        // Border for chessboard
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 6f
        canvas.drawRect(boardStartX, boardStartY, boardStartX + boardSize, boardStartY + boardSize, paint)

        // --- Cube in the middle ---
        val cubeSize = squareSize * 3f
        val startX = fullWidth / 2f - cubeSize / 2f
        val startY = boardStartY + boardSize + 60f

        paint.strokeWidth = 6f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE

        // Front square
        canvas.drawRect(startX, startY, startX + cubeSize, startY + cubeSize, paint)

        // Back square
        val offset = cubeSize / 3f
        canvas.drawRect(
            startX + offset, startY - offset,
            startX + cubeSize + offset, startY + cubeSize - offset,
            paint
        )

        // Connect corners
        canvas.drawLine(startX, startY, startX + offset, startY - offset, paint)
        canvas.drawLine(startX + cubeSize, startY, startX + cubeSize + offset, startY - offset, paint)
        canvas.drawLine(startX, startY + cubeSize, startX + offset, startY + cubeSize - offset, paint)
        canvas.drawLine(startX + cubeSize, startY + cubeSize, startX + cubeSize + offset, startY + cubeSize - offset, paint)

        // --- Cylinder BELOW cube ---
        val cylinderHeight = cubeSize * 1.2f
        val cylWidth = cubeSize * 0.8f
        val cylX = fullWidth / 2f - cylWidth / 2f
        val cylY = startY + cubeSize + 80f

        paint.color = Color.BLUE
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE

        // Top ellipse
        val topOval = RectF(cylX, cylY, cylX + cylWidth, cylY + cylWidth / 3f)
        canvas.drawOval(topOval, paint)

        // Bottom ellipse
        val bottomOval = RectF(cylX, cylY + cylinderHeight, cylX + cylWidth, cylY + cylinderHeight + cylWidth / 3f)
        canvas.drawOval(bottomOval, paint)

        // Sides
        canvas.drawLine(cylX, cylY + cylWidth / 6f, cylX, cylY + cylinderHeight + cylWidth / 6f, paint)
        canvas.drawLine(cylX + cylWidth, cylY + cylWidth / 6f, cylX + cylWidth, cylY + cylinderHeight + cylWidth / 6f, paint)

        // --- Glass of Water BELOW cylinder ---
        val glassTopWidth = cubeSize * 1.2f
        val glassBottomWidth = cubeSize * 0.7f
        val glassHeight = cubeSize * 1.5f

        val glassTopX = fullWidth / 2f - glassTopWidth / 2f
        val glassTopY = cylY + cylinderHeight + 120f
        val glassBottomX = fullWidth / 2f - glassBottomWidth / 2f
        val glassBottomY = glassTopY + glassHeight

        // Glass outline paint
        paint.color = Color.MAGENTA
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        paint.alpha = 180

        // Top ellipse (rim)
        val glassTopOval = RectF(glassTopX, glassTopY, glassTopX + glassTopWidth, glassTopY + glassTopWidth / 4f)
        canvas.drawOval(glassTopOval, paint)

        // Bottom ellipse (base)
        val glassBottomOval = RectF(glassBottomX, glassBottomY, glassBottomX + glassBottomWidth, glassBottomY + glassBottomWidth / 4f)
        canvas.drawOval(glassBottomOval, paint)

        // Sides of glass
        canvas.drawLine(glassTopX, glassTopY + glassTopWidth / 8f, glassBottomX, glassBottomY + glassBottomWidth / 8f, paint)
        canvas.drawLine(glassTopX + glassTopWidth, glassTopY + glassTopWidth / 8f, glassBottomX + glassBottomWidth, glassBottomY + glassBottomWidth / 8f, paint)

        // -------- WATER INSIDE GLASS --------
        val waterLevel = glassTopY + glassHeight * 0.6f

        // Water shape
        val waterPath = Path().apply {
            moveTo(glassBottomX, waterLevel)
            lineTo(glassBottomX, glassBottomY + glassBottomWidth / 8f)
            arcTo(glassBottomOval, 180f, -180f, false)
            lineTo(glassBottomX + glassBottomWidth, waterLevel)
            arcTo(
                RectF(
                    glassTopX + (glassTopWidth - glassBottomWidth) / 2f,
                    waterLevel - glassBottomWidth / 8f,
                    glassBottomX + glassBottomWidth,
                    waterLevel + glassBottomWidth / 8f
                ), 0f, 180f, false
            )
            close()
        }

        val shader = LinearGradient(
            0f, waterLevel, 0f, glassBottomY,
            Color.argb(150, 173, 216, 230),
            Color.argb(200, 30, 144, 255),
            Shader.TileMode.CLAMP
        )
        val waterPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            this.shader = shader
        }
        canvas.drawPath(waterPath, waterPaint)

        // Water surface ellipse
        val waterSurfaceOval = RectF(
            glassTopX + (glassTopWidth - glassBottomWidth) / 2f,
            waterLevel - glassBottomWidth / 8f,
            glassBottomX + glassBottomWidth,
            waterLevel + glassBottomWidth / 8f
        )
        canvas.drawOval(waterSurfaceOval, waterPaint)
    }
}
