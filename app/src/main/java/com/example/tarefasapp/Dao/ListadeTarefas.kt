package com.example.tarefasapp.Dao

val tarefa = Tarefa(id = 0, nome = "null", descricao = "null", prioridade = 0)

object listadetarefas {
    val tarefa =
        Tarefa(id = 0, nome = "null", descricao = "null", prioridade = 0)
    val tarefasList = listOf(
        tarefa,
        Tarefa(id = 1, nome = "tarefa", descricao = "tarefa 1", prioridade = 1),
        Tarefa(id = 2, nome = "Cozinhar Frango", descricao = "Às 11:33 devo fazer o frango Assado", prioridade = 1),
        Tarefa(id = 3, nome = "Concertar Bike", descricao = "Às 12:00 devo levar a Bike pro concerto", prioridade = 1)

    )
     }