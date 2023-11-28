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

    var noteUiStateEdit by mutableStateOf(NoteUiStateEdit())
        private set

    fun updateUiStateEdit(noteDetails: NoteDetailsEdit) {
        noteUiStateEdit =
            NoteUiStateEdit(noteDetails = noteDetails, isEntryValid = validateInputEdit(noteDetails))
    }

    suspend fun updateNote() {
        if (validateInputEdit()) {
            noteUiStateEdit.noteDetails.tipo=optionNote
            notesRepository.updateNote(noteUiStateEdit.noteDetails.toNoteEdit())
        }
    }

    private fun validateInputEdit(uiState: NoteDetailsEdit = noteUiStateEdit.noteDetails): Boolean {
        return with(uiState) {
            titulo.isNotBlank() && contenido.isNotBlank() && fecha.isNotBlank()
        }
    }

    var isExpanded by mutableStateOf(false)
    var optionNote by mutableStateOf(NoteUiState().noteDetails.tipo)
    var showCancelEdit by mutableStateOf(false)

    fun updateShowCancelEdit(boolean: Boolean){
        showCancelEdit= boolean
    }

    fun updateIsExpandedEdit(boolean: Boolean){
        isExpanded= boolean
    }

    fun updateOptionNoteEdit(text: String ){
        optionNote=text
    }
}

data class NoteUiStateEdit(
    val noteDetails: NoteDetailsEdit = NoteDetailsEdit(),
    val isEntryValid: Boolean = false
)

data class NoteDetailsEdit(
    val id: Int = 0,
    val titulo: String = "",
    val contenido: String = "",
    val fecha: String = ""+ Calendar.getInstance(TimeZone.getTimeZone("America/Mexico_City")).get(Calendar.DAY_OF_MONTH)+
            "/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR),
    var tipo:String="",
    val uriImagenes:String="",
    val uriVideos:String="",
    val uriAudios:String=""
)

fun NoteDetailsEdit.toNoteEdit(): NotaEntity = NotaEntity(
    id = id,
    titulo = titulo,
    contenido = contenido,
    fecha = fecha,
    tipo=tipo,
    uriImagenes= uriImagenes,
    uriVideos= uriVideos,
    uriAudios= uriAudios
)

fun NotaEntity.toNoteUiStateEdit(isEntryValid: Boolean = false): NoteUiState = NoteUiState(
    noteDetails = this.toNoteDetails(),
    isEntryValid = isEntryValid
)

fun NotaEntity.toNoteDetailsEdit(): NoteDetailsEdit = NoteDetailsEdit(
    id = id,
    titulo = titulo,
    contenido = contenido,
    fecha = fecha,
    tipo= tipo,
    uriImagenes= uriImagenes,
    uriVideos= uriVideos,
    uriAudios= uriAudios
)
