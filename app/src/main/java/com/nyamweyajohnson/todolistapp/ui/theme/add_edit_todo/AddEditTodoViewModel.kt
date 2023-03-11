package com.nyamweyajohnson.todolistapp.ui.theme.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyamweyajohnson.todolistapp.data.ToDo
import com.nyamweyajohnson.todolistapp.data.TodoRepository
import com.nyamweyajohnson.todolistapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    //this state contains the state of our navigation arguments
    //it can also contain state in case of system initiated processes death
    savedStateHandle: SavedStateHandle
): ViewModel() {
    //ToDo is nullable because if we want to add a new todo it will remain null
    //state of the ToDo that we loaded here from the ToDoListScreen
    var todo by mutableStateOf<ToDo?>(null)
        //set means we can change the value from within our viewModel but read it from outside
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    //events that we send from our viewModel to the UI e.g. popBackStack
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    //here we check if we opened the AddEditToDoScreen by clicking on a todo or add FAB
    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!

        if (todoId != -1){
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    this@AddEditTodoViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent){
        when(event){
            is AddEditTodoEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditTodoEvent.OnDescriptionChange -> {
                description = event.description
            }
            is AddEditTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if (title.isBlank()){
                        sendUiEvent(UiEvent.ShowSnackBar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertTodo(
                        //construct a new todo
                        ToDo(
                            title = title,
                            description = description,
                            //if we loaded the todo, we keep the isDone state
                            //if not we keep it false
                            isDone = todo?.isDone ?: false,
                            id = todo?.id
                        )
                    )
                    //if we finish the insertion, we navigate back
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}