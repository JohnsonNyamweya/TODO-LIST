package com.nyamweyajohnson.todolistapp.di

import android.app.Application
import androidx.room.Room
import com.nyamweyajohnson.todolistapp.data.ToDoDatabase
import com.nyamweyajohnson.todolistapp.data.TodoRepository
import com.nyamweyajohnson.todolistapp.data.TodoRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//this module define the dependencies we need in our project and also their lifetime
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    // this function will need the application context to be created so we pass it.
    fun provideTodoDatabase(app: Application): ToDoDatabase{
        return Room.databaseBuilder(
            app,
            ToDoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: ToDoDatabase): TodoRepository{
        return TodoRepositoryImplementation(db.dao)
    }
}