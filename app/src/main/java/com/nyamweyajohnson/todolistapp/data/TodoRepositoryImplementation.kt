package com.nyamweyajohnson.todolistapp.data

import kotlinx.coroutines.flow.Flow

class TodoRepositoryImplementation(
    //database instance to access the database
    private val dao: TodoDAO
): TodoRepository {

    override suspend fun insertTodo(toDo: ToDo) {
        dao.insertTodo(toDo)
    }

    override suspend fun deleteTodo(toDo: ToDo) {
        dao.deleteTodo(toDo)
    }

    override suspend fun getTodoById(id: Int): ToDo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<ToDo>> {
        return dao.getTodos()
    }
}