package com.funso.totrick

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date
import kotlin.random.Random

@Parcelize
data class TodoItem(
    val id: Int = Random.nextInt(),   //  Unique identifier  Automatically generates a random ID
    val title: String,      // Title of the task
    val createdAt: Date = Date(),  // Date the task was created. Automatically sets the current date
    val comment: String,    // Optional comment or description
    val status: Boolean //true = Completed, false = Pending
) : Parcelable
