package com.example.proyectofinal_jma.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * ViewModel para traer, actualizar y borrar una nota de la fuente de datos de NotesRepository.
 */

class NoteDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for ItemDetailsScreen
 */
data class NoteDetailsUiState(
    val outOfStock: Boolean = true,
    val itemDetails: NoteDetails = NoteDetails()
)
