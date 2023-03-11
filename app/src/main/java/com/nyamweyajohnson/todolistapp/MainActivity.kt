package com.nyamweyajohnson.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nyamweyajohnson.todolistapp.ui.theme.add_edit_todo.AddEditTodoScreen
import com.nyamweyajohnson.todolistapp.ui.theme.todo_list.ToDoListScreen
import com.nyamweyajohnson.todolistapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Routes.TODO_LIST
            ){
               composable(Routes.TODO_LIST){
                   ToDoListScreen(
                       onNavigate = {
                           navController.navigate(it.route)
                       }
                   )
               }
                composable(
                    route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                    arguments = listOf(
                        navArgument(name = "todoId"){
                            type = NavType.IntType
                            defaultValue = -1
                        }
                    )
                ){
                    AddEditTodoScreen(onPopBackStack = {
                        navController.popBackStack()
                    })
                }
            }
        }
    }
}