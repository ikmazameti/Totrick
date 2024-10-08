package com.funso.totrick


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.funso.totrick.databinding.ItemTodoBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TodoAdapter(private val onItemClicked: (todo: TodoItem) -> Unit) :
    ListAdapter<TodoItem, TodoAdapter.TodoViewHolder>(TodoDiff) {

    // Date Formatter for "dd MMM yyyy, HH:mm" format
    private val dateFormatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())

    // Function to format the date
    private fun formatDate(date: Date) = dateFormatter.format(date)


    inner class TodoViewHolder(
        private val binding: ItemTodoBinding,
        private val onItemClicked: (todo: TodoItem) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(todoItem: TodoItem) {
            val context = binding.root.context

            binding.title.text = todoItem.title
            binding.comment.text = todoItem.comment

            binding.createdAt.text =
                context.getString(R.string.created_at, formatDate(todoItem.createdAt))

            binding.status.text = if (todoItem.status) {
                context.getString(R.string.status_completed)
            } else {
                context.getString(
                    R.string.status_pending
                )
            }


            binding.status.setTextColor(
                if (todoItem.status) context.getColor(R.color.completed) else context.getColor(
                    R.color.pending
                )
            )


            binding.root.setOnClickListener { onItemClicked(todoItem) }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onItemClicked
        )


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    object TodoDiff : DiffUtil.ItemCallback<TodoItem>() {
        override fun areItemsTheSame(
            oldItem: TodoItem, newItem: TodoItem
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: TodoItem, newItem: TodoItem
        ): Boolean = oldItem.id == newItem.id
    }


}