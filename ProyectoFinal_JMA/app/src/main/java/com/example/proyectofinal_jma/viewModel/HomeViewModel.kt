package com.example.proyectofinal_jma.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal_jma.data.NotaEntity
import com.example.proyectofinal_jma.data.NotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel to retrieve all items in the Room database.
 */
class HomeViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {
    val homeUiState: StateFlow<HomeUiState> =
        notesRepository.getAllNotesStream().map{ HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    suspend fun deleteNote(note: NotaEntity) {
        notesRepository.deleteNote(note)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val noteList: List<NotaEntity> = listOf())