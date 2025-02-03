package com.example.db_management

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Random

class LoginActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper
    private var currentCaptcha: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dbHelper = DBHelper(this)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etCaptcha = findViewById<EditText>(R.id.etCaptcha)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRefreshCaptcha = findViewById<Button>(R.id.btnRefreshCaptcha)
        val captchaTextView = findViewById<TextView>(R.id.captchaText)

        // Generate the initial CAPTCHA
        generateCaptcha()

        // Refresh CAPTCHA button
        btnRefreshCaptcha.setOnClickListener {
            generateCaptcha()
        }

        // Login button functionality
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val enteredCaptcha = etCaptcha.text.toString()

            if (dbHelper.validateUser(username, password)) {
                if (enteredCaptcha == currentCaptcha) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, DashboardActivity::class.java))
                } else {
                    Toast.makeText(this, "Invalid CAPTCHA", Toast.LENGTH_SHORT).show()
                    generateCaptcha()
                }
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateCaptcha() {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val captcha = StringBuilder()
        val random = Random()
        for (i in 0..5) {
            captcha.append(characters[random.nextInt(characters.length)])
        }
        currentCaptcha = captcha.toString()
        findViewById<TextView>(R.id.captchaText).text = currentCaptcha
    }
}
