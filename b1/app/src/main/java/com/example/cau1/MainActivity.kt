package com.example.cau1


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val saveButton = findViewById<Button>(R.id.btnSave)
        val loadButton = findViewById<Button>(R.id.btnLoad)

        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        saveButton.setOnClickListener {
            val textToSave = editText.text.toString()
            if (textToSave.isNotBlank()) {
                with(sharedPreferences.edit()) {
                    putString("saved_text", textToSave)
                    apply()
                }
                Toast.makeText(this, "Dữ liệu đã được lưu!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vui lòng nhập dữ liệu trước khi lưu.", Toast.LENGTH_SHORT).show()
            }
        }

        loadButton.setOnClickListener {
            val savedText = sharedPreferences.getString("saved_text", null)
            if (savedText != null) {
                Toast.makeText(this, "Dữ liệu đã lưu: $savedText", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Không có dữ liệu nào được lưu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}