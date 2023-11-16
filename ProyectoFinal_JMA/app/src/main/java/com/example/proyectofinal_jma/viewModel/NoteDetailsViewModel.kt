package com.example.proyectofinal_jma.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal_jma.data.NotesRepository
import com.example.proyectofinal_jma.navigation.AppScreens
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel para traer, actualizar y borrar una nota de la fuente de datos de NotesRepository.
 */

class NoteDetailsViewModel(
    savedStateHandle: SavedStateHandle,

) : ViewModel() {

    private val noteId: Int = checkNotNull(savedStateHandle[AppScreens.AddScreen.route])
    /*
    val uiState: StateFlow<NoteDetailsUiState> =
        notesRepository.getNoteStream(noteId)
            .filterNotNull()
            .map {
                NoteDetailsUiState(noteDetails = it.toNoteDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = NoteDetailsUiState()
            )
    fun validarEdicion() {
        viewModelScope.launch {
            val currentNote = uiState.value.noteDetails.toNote()
            uiState.value.editar = currentNote.id>0
        }
    }*/

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

