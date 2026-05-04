package com.example.bmicalculator

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

        val txtWeight=findViewById<EditText>(R.id.txtWeight)
        val txtHeight=findViewById<EditText>(R.id.txtHeight)
        val txtResult=findViewById<TextView>(R.id.txtResult)
        val txtCalulate=findViewById<Button>(R.id.txtCalculate)

        txtCalulate.setOnClickListener {
            if(txtWeight.text.isEmpty() || txtHeight.text.isEmpty()){
                txtResult.text="Please enter all values!"
                return@setOnClickListener
            }

            val weight=txtWeight.text.toString().toFloat()
            val height=txtHeight.text.toString().toFloat()
            val heightm=height/100
            val bmi=weight/(heightm*heightm)
            val status=when{
                bmi<18.5->"Underweight"
                bmi<25->"Normal"
                bmi<30->"Overweight"
                else->"Obese"
            }
            txtResult.text="BMI: %.2f (%s) ".format(bmi,status)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}