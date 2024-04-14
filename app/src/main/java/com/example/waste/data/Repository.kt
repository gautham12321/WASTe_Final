package com.example.waste.data

import android.content.Context
import androidx.room.Room

class Repository(context: Context) {
    val db = Room.databaseBuilder(
    context,
    AppDatabase::class.java, "database-name"
).build()


}