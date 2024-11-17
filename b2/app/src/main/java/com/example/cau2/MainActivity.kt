package com.example.cau2

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private lateinit var darkModeSwitch: Switch
    private lateinit var textSizeRadioGroup: RadioGroup
    private lateinit var preferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ view
        darkModeSwitch = findViewById(R.id.switchDarkMode)
        textSizeRadioGroup = findViewById(R.id.radioGroupTextSize)

        // Khởi tạo SharedPreferences
        preferences = getSharedPreferences("UserSettings", Context.MODE_PRIVATE)

        // Khôi phục cài đặt từ SharedPreferences
        restoreSettings()

        // Lưu trạng thái của Switch cho chế độ tối
        darkModeSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            with(preferences.edit()) {
                putBoolean("dark_mode", isChecked)
                apply()
            }
            // Áp dụng chế độ tối
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // Lưu trạng thái của RadioButton cho kích thước chữ và thay đổi ngay lập tức
        textSizeRadioGroup.setOnCheckedChangeListener { _: RadioGroup, checkedId: Int ->
            val textSize = when (checkedId) {
                R.id.radioSmall -> "small"
                R.id.radioMedium -> "medium"
                R.id.radioLarge -> "large"
                else -> "medium"
            }
            with(preferences.edit()) {
                putString("text_size", textSize)
                apply()
            }
            // Thay đổi kích thước chữ ngay lập tức
            applyTextSize(textSize)
        }
    }

    private fun restoreSettings() {
        // Khôi phục chế độ tối
        val isDarkMode = preferences.getBoolean("dark_mode", false)
        darkModeSwitch.isChecked = isDarkMode
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Khôi phục kích thước chữ
        val textSize = preferences.getString("text_size", "medium")
        when (textSize) {
            "small" -> textSizeRadioGroup.check(R.id.radioSmall)
            "medium" -> textSizeRadioGroup.check(R.id.radioMedium)
            "large" -> textSizeRadioGroup.check(R.id.radioLarge)
        }
        // Áp dụng kích thước chữ ngay lập tức khi khôi phục cài đặt
        applyTextSize(textSize ?: "medium")
    }

    private fun applyTextSize(textSize: String) {
        val scale = when (textSize) {
            "small" -> 0.4f
            "medium" -> 1.0f
            "large" -> 2.4f
            else -> 1.0f
        }

        // Thay đổi kích thước chữ cho toàn bộ ứng dụng hoặc các view cần thiết
        val config = resources.configuration
        config.fontScale = scale
        resources.updateConfiguration(config, resources.displayMetrics)

        // Làm mới giao diện ngay lập tức
        recreate() // Gọi lại onCreate() để áp dụng thay đổi ngay lập tức
    }
}
