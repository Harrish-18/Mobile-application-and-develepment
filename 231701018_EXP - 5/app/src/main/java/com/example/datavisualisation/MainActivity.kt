package com.example.datavisualisation

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val labelsInput = findViewById<TextInputEditText>(R.id.labelsInput)
        val valuesInput = findViewById<TextInputEditText>(R.id.valuesInput)
        val visualizeButton = findViewById<Button>(R.id.visualizeButton)
        val barChartView = findViewById<BarChartView>(R.id.barChartView)

        visualizeButton.setOnClickListener {
            val labelsStr = labelsInput.text.toString()
            val valuesStr = valuesInput.text.toString()

            if (labelsStr.isBlank() || valuesStr.isBlank()) {
                Toast.makeText(this, "Please enter both labels and values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val labels = labelsStr.split(",").map { it.trim() }
            val values = valuesStr.split(",").mapNotNull { it.trim().toFloatOrNull() }

            if (labels.size != values.size) {
                Toast.makeText(this, "Number of labels and values must match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = labels.zip(values)
            barChartView.setData(data)
        }
    }
}
