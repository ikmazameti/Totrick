package com.funso.totrick

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.funso.totrick.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var todoItem: TodoItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable back button
        binding.materialToolbar.setNavigationOnClickListener {
            finish() // Handle back button click
        }


        // Get the passed TodoItem object
        todoItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(TODO_ITEM_KEY, TodoItem::class.java)
        } else {
            intent.getParcelableExtra(TODO_ITEM_KEY)
        }

        binding.textView.text = todoItem?.title


    }
}