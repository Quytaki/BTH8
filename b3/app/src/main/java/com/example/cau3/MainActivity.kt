package com.example.cau3

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences
    private lateinit var countTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ TextView để hiển thị số lần mở
        countTextView = findViewById(R.id.textViewCount)

        // Khởi tạo SharedPreferences
        preferences = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

        // Lấy số lần mở ứng dụng từ SharedPreferences
        val openCount = preferences.getInt("open_count", 0)

        // Tăng số lần mở lên 1
        val updatedCount = openCount + 1

        // Lưu lại số lần mở ứng dụng mới vào SharedPreferences
        with(preferences.edit()) {
            putInt("open_count", updatedCount)
            apply()
        }

        // Hiển thị số lần mở ứng dụng trên màn hình
        countTextView.text = "Số lần mở ứng dụng: $updatedCount"
    }
}