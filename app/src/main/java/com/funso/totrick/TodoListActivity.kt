package com.funso.totrick

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.funso.totrick.databinding.ActivityTodoListBinding

class TodoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoListBinding
    private lateinit var todoAdapter: TodoAdapter
    private val todoList = mutableListOf(
        TodoItem(
            title = "Pay Bills",
            comment = "Pay electricity and internet bills.",
            status = false,
        ),
        TodoItem(
            title = "Book Doctor Appointment",
            comment = "Schedule a check-up appointment with Dr. Smith.",
            status = true
        ),
        TodoItem(
            title = "Grocery Shopping",
            comment = "Buy milk, eggs, bread, and cheesefrom the supermarket.",
            status = false
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        todoAdapter = TodoAdapter() {}
        todoAdapter.submitList(todoList)

        binding.todoItems.adapter = todoAdapter

//Genymotion

    }


}