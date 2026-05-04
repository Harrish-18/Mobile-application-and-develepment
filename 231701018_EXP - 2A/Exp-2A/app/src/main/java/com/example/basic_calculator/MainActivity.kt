package com.example.basic_calculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val txtno1 = findViewById<EditText>(R.id.txtno1)
        val txtno2 = findViewById<EditText>(R.id.txtno2)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        val btnadd = findViewById<Button>(R.id.btnadd)
        val btnsub = findViewById<Button>(R.id.btnsub)
        val btnmul = findViewById<Button>(R.id.btnmul)
        val btndiv = findViewById<Button>(R.id.btndiv)
        val btnmod = findViewById<Button>(R.id.btnmod)

        btnadd.setOnClickListener {
            if (txtno1.text.toString().isEmpty() || txtno2.text.toString().isEmpty()) {
                txtResult.text = "Enter both numbers"
            } else {
                val n1 = txtno1.text.toString().toInt()
                val n2 = txtno2.text.toString().toInt()
                txtResult.text = (n1 + n2).toString()
            }
        }

        btnsub.setOnClickListener {
            if (txtno1.text.toString().isEmpty() || txtno2.text.toString().isEmpty()) {
                txtResult.text = "Enter both numbers"
            } else {
                val n1 = txtno1.text.toString().toInt()
                val n2 = txtno2.text.toString().toInt()
                txtResult.text = (n1 - n2).toString()
            }
        }

        btnmul.setOnClickListener {
            if (txtno1.text.toString().isEmpty() || txtno2.text.toString().isEmpty()) {
                txtResult.text = "Enter both numbers"
            } else {
                val n1 = txtno1.text.toString().toInt()
                val n2 = txtno2.text.toString().toInt()
                txtResult.text = (n1 * n2).toString()
            }
        }

        btndiv.setOnClickListener {
            if (txtno1.text.toString().isEmpty() || txtno2.text.toString().isEmpty()) {
                txtResult.text = "Enter both numbers"
            } else {
                val n1 = txtno1.text.toString().toInt()
                val n2 = txtno2.text.toString().toInt()

                if (n2 == 0) {
                    txtResult.text = "Cannot divide by zero"
                } else {
                    txtResult.text = (n1 / n2).toString()
                }
            }
        }

        btnmod.setOnClickListener {
            if (txtno1.text.toString().isEmpty() || txtno2.text.toString().isEmpty()) {
                txtResult.text = "Enter both numbers"
            } else {
                val n1 = txtno1.text.toString().toInt()
                val n2 = txtno2.text.toString().toInt()
                txtResult.text = (n1 % n2).toString()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}