package com.example.imagify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab=findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        fab.setOnClickListener{
            val intent= Intent(this@MainActivity,Details::class.java)
            startActivity(intent)

        }
    }
}