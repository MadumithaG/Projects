package com.example.db_management

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dbHelper = DBHelper(this)

        val etRollNo = findViewById<EditText>(R.id.etRollNo)
        val etName = findViewById<EditText>(R.id.etName)
        val etMarks = findViewById<EditText>(R.id.etMarks)
        val btnInsert = findViewById<Button>(R.id.btnInsert)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnViewAll = findViewById<Button>(R.id.btnViewAll)

        // Insert Student
        btnInsert.setOnClickListener {
            val rollNoText = etRollNo.text.toString()
            val name = etName.text.toString()
            val marksText = etMarks.text.toString()

            if (rollNoText.isNotEmpty() && name.isNotEmpty() && marksText.isNotEmpty()) {
                val rollNo = rollNoText.toInt()
                val marks = marksText.toInt()

                if (dbHelper.insertStudent(rollNo, name, marks)) {
                    Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to Add", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }

        // Update Student
        btnUpdate.setOnClickListener {
            val rollNoText = etRollNo.text.toString()
            val name = etName.text.toString()
            val marksText = etMarks.text.toString()

            if (rollNoText.isNotEmpty() && name.isNotEmpty() && marksText.isNotEmpty()) {
                val rollNo = rollNoText.toInt()
                val marks = marksText.toInt()

                if (dbHelper.updateStudent(rollNo, name, marks)) {
                    Toast.makeText(this, "Student Updated", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }

        // Delete Student
        btnDelete.setOnClickListener {
            val rollNoText = etRollNo.text.toString()

            if (rollNoText.isNotEmpty()) {
                val rollNo = rollNoText.toInt()

                if (dbHelper.deleteStudent(rollNo)) {
                    Toast.makeText(this, "Student Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter Roll No to delete", Toast.LENGTH_SHORT).show()
            }
        }

        // View All Students
        btnViewAll.setOnClickListener {
            startActivity(Intent(this, ViewActivity::class.java))
        }
    }
}
