package com.example.cau4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)

        preferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        // Kiểm tra nếu người dùng đã đăng nhập trước đó
        val isLoggedIn = preferences.getBoolean("is_logged_in", false)
        if (isLoggedIn) {
            navigateToMainScreen()
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Kiểm tra thông tin đăng nhập (mẫu)
            if (username == "admin" && password == "12345") {
                // Lưu trạng thái đăng nhập
                with(preferences.edit()) {
                    putBoolean("is_logged_in", true)
                    apply()
                }
                navigateToMainScreen()
            } else {
                // Thông báo lỗi đăng nhập
                usernameEditText.error = "Sai tên người dùng hoặc mật khẩu"
            }
        }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Đóng màn hình đăng nhập khi chuyển sang màn hình chính
    }
}
