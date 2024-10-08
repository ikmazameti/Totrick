package com.funso.totrick

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.funso.totrick.databinding.ActivityAddTodoBinding

class AddTodoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable back button
        binding.toolbar.setNavigationOnClickListener {
            finish() // Handle back button click
        }

        // Handle Save button click
        binding.btnSave.setOnClickListener {
           saveTodoItem()
        }


    }

    // Function to save the To-Do item
    private fun saveTodoItem() {
        // Get the title, comment, and status from the input fields
        val title = binding.etTitle.text.toString().trim()
        val comment = binding.etComment.text.toString().trim()
        val status = binding.switchStatus.isChecked // True if the switch is checked

        // Validate that the title is not empty
        if (title.isEmpty()) {
            binding.etTitle.error = "Title is required"
            return
        }

        // Create a new TodoItem object
        val newTodoItem = TodoItem(
            title = title, comment = comment, status = status
        )

        // Return the result to MainActivity
        val resultIntent = Intent()
        resultIntent.putExtra(NEW_TODO_ITEM_KEY, newTodoItem)
        setResult(RESULT_OK, resultIntent)
        finish() // Close the activity
    }
}