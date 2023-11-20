package com.example.proyectofinal_jma.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal_jma.data.NotaEntity
import com.example.proyectofinal_jma.data.NotesRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.TimeZone

class NoteEditViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    var noteUiStateEdit by mutableStateOf(NoteUiState())
        private set

    fun updateUiStateEdit(noteDetails: NoteDetails) {
        noteUiStateEdit =
            NoteUiState(noteDetails = noteDetails, isEntryValid = validateInputEdit(noteDetails))
    }

    suspend fun updateNote() {
        if (validateInputEdit()) {
            notesRepository.updateNote(noteUiStateEdit.noteDetails.toNote())
        }
    }

    private fun validateInputEdit(uiState: NoteDetails = noteUiStateEdit.noteDetails): Boolean {
        return with(uiState) {
            titulo.isNotBlank() && contenido.isNotBlank() && fecha.isNotBlank()
        }
    }

    var isExpanded by mutableStateOf(false)
    var isExpanded2 by mutableStateOf(false)
    var optionNote by mutableStateOf("Nota")
    var sizeText by mutableStateOf("Normal")

    fun updateIsExpandedEdit(boolean: Boolean){
        isExpanded= boolean
    }

    fun updateIsExpandend2Edit(boolean: Boolean){
        isExpanded2= boolean
    }

    fun updateOptionNoteEdit(text: String ){
        optionNote=text
    }

    fun updatesizeTextEdit(text: String ){
        sizeText=text
    }

}
