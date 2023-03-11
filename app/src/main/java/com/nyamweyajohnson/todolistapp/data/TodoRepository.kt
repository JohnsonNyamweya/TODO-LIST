package com.nyamweyajohnson.todolistapp.data

import kotlinx.coroutines.flow.Flow

//decides which data from which source to be forwarded to the viewModel
interface TodoRepository {

    suspend fun insertTodo(toDo: ToDo)

    suspend fun deleteTodo(toDo: ToDo)

    suspend fun getTodoById(id: Int): ToDo?

    fun getTodos(): Flow<List<ToDo>>
}