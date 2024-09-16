package com.example.tarefasapp.Dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tarefa::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): TarefaDao
}