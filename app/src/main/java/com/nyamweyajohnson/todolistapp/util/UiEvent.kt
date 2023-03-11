package com.nyamweyajohnson.todolistapp.util

//events sent from the viewModel to the UI when something in the viewModel changes
sealed class UiEvent{
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UiEvent()
}
