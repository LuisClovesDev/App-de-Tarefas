package com.example.tarefasapp.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TarefaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tarefa: Tarefa)

    @Update
    suspend fun update(tarefa: Tarefa)

    @Delete
    suspend fun delete(tarefa: Tarefa)

    @Query("SELECT * FROM Tarefa")
    fun getAll(): Flow<List<Tarefa>>

    @Query("SELECT * FROM Tarefa WHERE id = :id")
    suspend fun getById(id: Int): Tarefa?

    @Query("DELETE FROM Tarefa")
    suspend fun deleteAll()
}