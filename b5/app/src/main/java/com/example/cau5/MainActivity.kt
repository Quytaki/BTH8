package com.example.cau5

import android.annotation.SuppressLint

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var taskInput: EditText
    private lateinit var addTaskButton: Button
    private lateinit var taskListView: ListView
    private lateinit var preferences: SharedPreferences

    private val taskList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskInput = findViewById(R.id.taskInput)
        addTaskButton = findViewById(R.id.addTaskButton)
        taskListView = findViewById(R.id.taskListView)

        preferences = getSharedPreferences("TaskPrefs", Context.MODE_PRIVATE)

        // Khôi phục danh sách tác vụ
        loadTasks()

        // Cập nhật ListView với adapter
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
        taskListView.adapter = adapter

        // Thêm tác vụ
        addTaskButton.setOnClickListener {
            val task = taskInput.text.toString()
            if (task.isNotEmpty()) {
                taskList.add(task)
                adapter.notifyDataSetChanged()
                taskInput.text.clear()
                saveTasks()
            } else {
                Toast.makeText(this, "Vui lòng nhập tác vụ!", Toast.LENGTH_SHORT).show()
            }
        }

        // Sửa hoặc xóa tác vụ khi nhấn giữ
        taskListView.setOnItemLongClickListener { _, _, position, _ ->
            val selectedTask = taskList[position]
            showTaskOptionsDialog(selectedTask, position)
            true
        }
    }

    private fun saveTasks() {
        val gson = Gson()
        val json = gson.toJson(taskList)
        with(preferences.edit()) {
            putString("tasks", json)
            apply()
        }
    }

    private fun loadTasks() {
        val gson = Gson()
        val json = preferences.getString("tasks", null)
        if (json != null) {
            val type = object : TypeToken<MutableList<String>>() {}.type
            val loadedTasks: MutableList<String> = gson.fromJson(json, type)
            taskList.clear()
            taskList.addAll(loadedTasks)
        }
    }

    private fun showTaskOptionsDialog(task: String, position: Int) {
        val options = arrayOf("Sửa", "Xóa")
        AlertDialog.Builder(this)
            .setTitle("Tùy chọn tác vụ")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> showEditTaskDialog(task, position) // Sửa tác vụ
                    1 -> deleteTask(position) // Xóa tác vụ
                }
            }
            .show()
    }

    private fun showEditTaskDialog(task: String, position: Int) {
        val editText = EditText(this)
        editText.setText(task)
        AlertDialog.Builder(this)
            .setTitle("Chỉnh sửa tác vụ")
            .setView(editText)
            .setPositiveButton("Lưu") { _, _ ->
                val newTask = editText.text.toString()
                if (newTask.isNotEmpty()) {
                    taskList[position] = newTask
                    adapter.notifyDataSetChanged()
                    saveTasks()
                }
            }
            .setNegativeButton("Hủy", null)
            .show()
    }

    private fun deleteTask(position: Int) {
        taskList.removeAt(position)
        adapter.notifyDataSetChanged()
        saveTasks()
    }
}
