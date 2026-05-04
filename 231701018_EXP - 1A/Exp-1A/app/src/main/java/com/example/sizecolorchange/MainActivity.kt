package com.example.sizecolorchange

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var textSize=20f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnIncrease=findViewById<Button>(R.id.btnIncrease)
        val btnDecrease=findViewById<Button>(R.id.btnDecrease)
        val btnTextColor=findViewById<Button>(R.id.btnTextColor)
        val btnBgColor=findViewById<Button>(R.id.btnBgColor)

        btnIncrease.setOnClickListener {
            val textView=findViewById<TextView>(R.id.textView)
            textSize+=2f
            textView.textSize=textSize
        }

        btnDecrease.setOnClickListener {
            val textView=findViewById<TextView>(R.id.textView)
            if(textSize>10f) {
                textSize -= 2f
                textView.textSize = textSize
            }
        }

        btnTextColor.setOnClickListener {
            val textView=findViewById<TextView>(R.id.textView)
            textView.setTextColor(randomColor())
        }

        btnBgColor.setOnClickListener {
            val mainLayout=findViewById<LinearLayout>(R.id.main)
            mainLayout.setBackgroundColor(randomColor())
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun randomColor(): Int {
        return Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
    }
}