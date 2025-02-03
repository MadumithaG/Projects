package com.example.db_management

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewActivity : AppCompatActivity() {
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        dbHelper = DBHelper(this)

        val tvRecords = findViewById<TextView>(R.id.tvRecords)
        val students = dbHelper.getAllStudents()
        tvRecords.text = students
    }
}
