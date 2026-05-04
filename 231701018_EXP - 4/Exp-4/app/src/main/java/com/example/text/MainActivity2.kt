package com.example.text

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class MainActivity2 : AppCompatActivity() {

    private lateinit var tv: TextView
    private lateinit var detector: GestureDetector

    private var startX = 0f
    private var startY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tv = findViewById(R.id.tv2)

        detector = GestureDetector(
            this,
            object : GestureDetector.SimpleOnGestureListener() {

                override fun onDown(e: MotionEvent): Boolean = true

                override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                    tv.setText(R.string.single_tap)
                    return true
                }

                override fun onDoubleTap(e: MotionEvent): Boolean {
                    tv.setText(R.string.double_tap)
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    tv.setText(R.string.long_press)
                }
            })

        val layout = findViewById<View>(R.id.main)

        layout.setOnTouchListener { view, event ->

            detector.onTouchEvent(event)

            when (event.action) {

                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                }

                MotionEvent.ACTION_UP -> {

                    val diffX = event.x - startX
                    val diffY = event.y - startY
                    val minDistance = 120

                    if (kotlin.math.abs(diffX) > kotlin.math.abs(diffY)) {

                        if (kotlin.math.abs(diffX) > minDistance) {
                            if (diffX > 0) {
                                tv.setText(R.string.swipe_right)
                            } else {
                                tv.setText(R.string.swipe_left)
                            }
                        }

                    } else {

                        if (kotlin.math.abs(diffY) > minDistance) {
                            if (diffY > 0) {
                                tv.setText(R.string.swipe_down)
                            } else {
                                tv.setText(R.string.swipe_up)
                            }
                        }
                    }

                    view.performClick()
                }
            }
            true
        }
    }
}