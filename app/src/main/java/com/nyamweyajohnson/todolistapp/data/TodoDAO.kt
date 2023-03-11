package com.nyamweyajohnson.todolistapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(toDo: ToDo)

    @Delete
    suspend fun deleteTodo(toDo: ToDo)

    @Query("SELECT * FROM todo WHERE id = :id ")
    suspend fun getTodoById(id: Int): ToDo?

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<ToDo>>
    //we are using a Flow to make sure that we get updates in real time as something happens
}