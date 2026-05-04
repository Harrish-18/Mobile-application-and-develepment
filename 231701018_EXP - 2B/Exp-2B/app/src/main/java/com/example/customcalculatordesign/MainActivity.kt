package com.example.customcalculatordesign

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    lateinit var txtResult: TextView

    var currentInput = ""
    var result = 0.0
    var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtResult = findViewById(R.id.txtResult)

        // Number buttons
        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val btnPnt = findViewById<Button>(R.id.btnPnt)

        // Operators
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnSub = findViewById<Button>(R.id.btnSub)
        val btnMul = findViewById<Button>(R.id.btnMul)
        val btnDiv = findViewById<Button>(R.id.btnDiv)
        val btnEql = findViewById<Button>(R.id.btnEql)
        val btnC = findViewById<Button>(R.id.btnC)

        // Scientific buttons
        val btnSin = findViewById<Button>(R.id.btnSin)
        val btnCos = findViewById<Button>(R.id.btnCos)
        val btnTan = findViewById<Button>(R.id.btnTan)
        val btnLog = findViewById<Button>(R.id.btnLog)
        val btnRoot = findViewById<Button>(R.id.btnRoot)

        // Number clicks
        btn0.setOnClickListener { addNumber("0") }
        btn1.setOnClickListener { addNumber("1") }
        btn2.setOnClickListener { addNumber("2") }
        btn3.setOnClickListener { addNumber("3") }
        btn4.setOnClickListener { addNumber("4") }
        btn5.setOnClickListener { addNumber("5") }
        btn6.setOnClickListener { addNumber("6") }
        btn7.setOnClickListener { addNumber("7") }
        btn8.setOnClickListener { addNumber("8") }
        btn9.setOnClickListener { addNumber("9") }

        // Decimal point
        btnPnt.setOnClickListener {
            if (!currentInput.contains(".")) {
                if (currentInput.isEmpty())
                    currentInput = "0."
                else
                    currentInput += "."
                updateDisplay()
            }
        }

        // Operators
        btnAdd.setOnClickListener { applyOperator("+") }
        btnSub.setOnClickListener { applyOperator("-") }
        btnMul.setOnClickListener { applyOperator("*") }
        btnDiv.setOnClickListener { applyOperator("/") }

        // Equal
        btnEql.setOnClickListener {
            calculate()
            txtResult.text = formatResult(result)
            currentInput = result.toString()
            operator = ""
        }

        // Clear
        btnC.setOnClickListener {
            currentInput = ""
            result = 0.0
            operator = ""
            txtResult.text = "0"
        }

        // Scientific
        btnSin.setOnClickListener {
            scientific("sin") { sin(Math.toRadians(it)) }
        }

        btnCos.setOnClickListener {
            scientific("cos") { cos(Math.toRadians(it)) }
        }

        btnTan.setOnClickListener {
            scientific("tan") { tan(Math.toRadians(it)) }
        }

        btnLog.setOnClickListener {
            scientific("log") { log10(it) }
        }

        btnRoot.setOnClickListener {
            scientific("√") { sqrt(it) }
        }
    }

    // Add number
    fun addNumber(value: String) {
        currentInput += value
        updateDisplay()
    }

    // Apply operator
    fun applyOperator(op: String) {
        if (currentInput.isEmpty() && result == 0.0) return
        calculate()
        operator = op
        txtResult.text = formatResult(result) + " " + op + " "
    }

    // Calculate result
    fun calculate() {

        if (currentInput.isEmpty()) return

        val number = currentInput.toDouble()

        result = when (operator) {
            "+" -> result + number
            "-" -> result - number
            "*" -> if (result == 0.0) number else result * number
            "/" -> result / number
            else -> number
        }

        currentInput = ""
    }

    // Scientific functions
    fun scientific(name: String, operation: (Double) -> Double) {

        if (currentInput.isEmpty()) return

        val inputValue = currentInput.toDouble()
        val value = operation(inputValue)

        txtResult.text =
            "$name($currentInput)\n${formatResult(value)}"

        currentInput = value.toString()
        result = value
        operator = ""
    }

    // Update display while typing
    fun updateDisplay() {
        if (operator.isEmpty())
            txtResult.text = currentInput
        else
            txtResult.text =
                formatResult(result) + " " + operator + " " + currentInput
    }

    // Remove unnecessary .0
    fun formatResult(value: Double): String {
        return if (value % 1 == 0.0)
            value.toInt().toString()
        else
            value.toString()
    }
}
