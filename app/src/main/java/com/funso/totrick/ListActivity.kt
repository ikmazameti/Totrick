package com.funso.totrick

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.funso.totrick.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {
    private lateinit var addTodoLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityListBinding
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

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        //Initialise adapter
        todoAdapter = TodoAdapter {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(TODO_ITEM_KEY, it)
            startActivity(intent)
        }
        todoAdapter.submitList(todoList)// supply data to adapter

        binding.todoItems.adapter = todoAdapter//connect TodoAdopter to RecyclerView

        // Register the ActivityResultLauncher to handle the result from AddTodoActivity
        addTodoLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                // Get the new ToDo item from the result data
                val newTodoItem = result.data?.getParcelableExtra<TodoItem>(NEW_TODO_ITEM_KEY)
                newTodoItem?.let {
                    // Handle the new item (e.g., add it to the list, save to database, etc.)
                    addNewItemToList(it)
                }
            }
        }


    }

    private fun addNewItemToList(newItem: TodoItem) {
        val newTodo = TodoItem(
            title = newItem.title,
            comment = newItem.comment,
            status = newItem.status
        )
        todoList.add(newTodo)
        todoAdapter.notifyItemInserted(todoList.size - 1) // Notify adapter of new item
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {


            R.id.addTodo -> {

                val intent = Intent(this, AddTodoActivity::class.java)
                addTodoLauncher.launch(intent)

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    
    
    
}