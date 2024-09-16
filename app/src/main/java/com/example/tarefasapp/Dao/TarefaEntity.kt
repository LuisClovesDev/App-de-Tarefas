package com.example.tarefasapp.Dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Tarefa(
    @PrimaryKey val id: Int,
    @ColumnInfo (name = "name") val nome: String,
    @ColumnInfo (name = "descricao") val descricao: String,
    @ColumnInfo (name = "prioridade") val prioridade: Int
): Serializable