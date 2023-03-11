package com.nyamweyajohnson.todolistapp.ui.theme.todo_list

import com.nyamweyajohnson.todolistapp.data.ToDo

//sends events from the UI to the viewModel
sealed class TodoListEvent{
    data class OnDeleteTodoClick(val todo: ToDo): TodoListEvent()
    data class OnDoneChange(val todo: ToDo, val isDone: Boolean): TodoListEvent()
    object OnUndoDeleteClick: TodoListEvent()
    data class OnTodoClick(val todo: ToDo): TodoListEvent()
    object OnAddTodoClick: TodoListEvent()
}
