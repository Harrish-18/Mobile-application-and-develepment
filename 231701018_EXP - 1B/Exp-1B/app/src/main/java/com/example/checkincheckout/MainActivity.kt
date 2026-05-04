package com.example.checkincheckout

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var count=0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val textView=findViewById<TextView>(R.id.textView)
        val btnCheckIn=findViewById<Button>(R.id.txtCheckIn)
        val btnCheckOut=findViewById<Button>(R.id.txtCheckOut)

        btnCheckIn.setOnClickListener {
            count++
            textView.text=count.toString()
        }

        btnCheckOut.setOnClickListener {
            if(count>0){
                count--
                textView.text=count.toString()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}