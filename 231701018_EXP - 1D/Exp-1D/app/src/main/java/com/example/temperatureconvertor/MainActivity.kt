package com.example.temperatureconvertor

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val txtTemp=findViewById<EditText>(R.id.txtTemp)
        val txtResult=findViewById<TextView>(R.id.txtResult)

        val btnCtoF=findViewById<Button>(R.id.btnCtoF)
        val btnFtoC=findViewById<Button>(R.id.btnFtoC)

        btnCtoF.setOnClickListener {
            if (txtTemp.text.isNotEmpty()) {
                val c = txtTemp.text.toString().toDouble()
                val f = (c * 9 / 5) + 32
                txtResult.text = "Celsius: %.2f --> Fahrenheit: %.2f".format(c, f)
            } else {
                txtResult.text = "Please enter the Temperature"
            }
        }

        btnFtoC.setOnClickListener {
            if (txtTemp.text.isNotEmpty()) {
                val f = txtTemp.text.toString().toDouble()
                val c = (f - 32) * 5 / 9
                txtResult.text = "Fahrenheit: %.2f --> Celsius: %.2f".format(f, c)
            } else {
                txtResult.text = "Enter a value"
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}