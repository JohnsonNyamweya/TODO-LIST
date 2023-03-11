package com.nyamweyajohnson.todolistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ToDo::class],
    version = 1
)

abstract class ToDoDatabase: RoomDatabase() {

    //dao reference which room automatically creates an instance for us
    abstract val dao: TodoDAO
}