package com.example.cau4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var logoutButton: Button
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logoutButton = findViewById(R.id.logoutButton)
        preferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        logoutButton.setOnClickListener {
            // Xóa trạng thái đăng nhập khi người dùng đăng xuất
            with(preferences.edit()) {
                remove("is_logged_in")
                apply()
            }

            // Quay lại màn hình đăng nhập
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Đóng màn hình chính
        }
    }
}