package com.example.db_management

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "StudentDB", null, 1) {

    // On database creation, create Users and Students tables
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Users(username TEXT PRIMARY KEY, password TEXT)")
        db.execSQL("CREATE TABLE Students(rollNo INTEGER PRIMARY KEY, name TEXT, marks INTEGER)")
    }

    // On upgrade, drop existing tables and recreate them
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Users")
        db.execSQL("DROP TABLE IF EXISTS Students")
        onCreate(db)
    }

    // Register new user
    fun registerUser(username: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("username", username)
            put("password", password)
        }
        return db.insert("Users", null, values) > 0
    }

    // Validate user credentials
    fun validateUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Users WHERE username=? AND password=?", arrayOf(username, password))
        return cursor.count > 0
    }

    // Insert new student record
    fun insertStudent(rollNo: Int, name: String, marks: Int): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("rollNo", rollNo)
            put("name", name)
            put("marks", marks)
        }
        return db.insert("Students", null, values) > 0
    }

    // Get all students records
    fun getAllStudents(): String {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Students", null)
        var result = ""
        while (cursor.moveToNext()) {
            result += "Roll No: ${cursor.getInt(0)}, Name: ${cursor.getString(1)}, Marks: ${cursor.getInt(2)}\n"
        }
        cursor.close()
        db.close()
        return result
    }

    // Update student details
    fun updateStudent(rollNo: Int, name: String, marks: Int): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("marks", marks)
        }
        val result = db.update("Students", values, "rollNo=?", arrayOf(rollNo.toString()))
        db.close()
        return result > 0
    }

    // Delete a student record by rollNo
    fun deleteStudent(rollNo: Int): Boolean {
        val db = writableDatabase
        val result = db.delete("Students", "rollNo=?", arrayOf(rollNo.toString()))
        db.close()
        return result > 0
    }
}
