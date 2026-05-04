package com.example.datavisualisation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BarChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var data: List<Pair<String, Float>> = emptyList()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 32f
        textAlign = Paint.Align.CENTER
    }

    fun setData(newData: List<Pair<String, Float>>) {
        data = newData
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (data.isEmpty()) return

        val maxValue = data.maxOf { it.second }
        if (maxValue == 0f) return

        val barSpacing = 20f
        val availableWidth = width.toFloat() - (data.size + 1) * barSpacing
        val barWidth = availableWidth / data.size
        val chartHeight = height.toFloat() - 100f // space for labels

        var currentX = barSpacing
        val colors = listOf(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA)

        data.forEachIndexed { index, pair ->
            val barHeight = (pair.second / maxValue) * chartHeight
            paint.color = colors[index % colors.size]
            
            val left = currentX
            val top = chartHeight - barHeight
            val right = currentX + barWidth
            val bottom = chartHeight

            canvas.drawRect(left, top, right, bottom, paint)
            
            // Draw label
            canvas.drawText(pair.first, left + barWidth / 2, chartHeight + 40f, labelPaint)
            // Draw value
            canvas.drawText(pair.second.toString(), left + barWidth / 2, top - 10f, labelPaint)

            currentX += barWidth + barSpacing
        }
    }
}
