package com.example.proyectofinal_jma.viewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SnapshotMutationPolicy
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal_jma.ItemEditDestination
import com.example.proyectofinal_jma.data.ImageNotaEntity
import com.example.proyectofinal_jma.data.NotaEntity
import com.example.proyectofinal_jma.data.NotesRepository
import com.example.proyectofinal_jma.data.VideoNotaEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.TimeZone

class NoteEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val notesRepository: NotesRepository
) : ViewModel() {

    var noteUiStateEdit by mutableStateOf(NoteUiStateEdit())
        private set
    var urislist= mutableStateListOf<Uri?>()
    var urisVideoslist= mutableStateListOf<Uri>()
    var optionNote by mutableStateOf(noteUiStateEdit.noteDetails.tipo)
    private val itemId: Int = checkNotNull(savedStateHandle["id"])

    init {
        viewModelScope.launch {
            noteUiStateEdit = notesRepository.getNoteStream(itemId)
                .filterNotNull()
                .first()
                .toNoteUiStateEdit(true)
            urislist.clear()
            urislist.addAll(getAllImages())
            updateCantidad()
            urisVideoslist.clear()
            urisVideoslist.addAll(getAllVideos())
            updateCantidadVideos()
        }
    }

    suspend fun getAllImages():SnapshotStateList<Uri?>{
        var lista=notesRepository.getAllImages(itemId).first()
        var lista2= mutableStateListOf<Uri?>()
        for((indice) in lista.withIndex()){
            lista2.add(Uri.parse(lista[indice]))
        }
        return lista2
    }

    suspend fun getAllVideos():SnapshotStateList<Uri>{
        var lista=notesRepository.getAllVideos(itemId).first()
        var lista2= mutableStateListOf<Uri>()
        for((indice) in lista.withIndex()){
            lista2.add(Uri.parse(lista[indice]))
        }
        return lista2
    }

    fun updateUiStateEdit(noteDetails: NoteDetailsEdit) {
        noteUiStateEdit =
            NoteUiStateEdit(noteDetails = noteDetails, isEntryValid = validateInputEdit(noteDetails))
    }

    suspend fun updateNote() {
        if (validateInputEdit()) {
            noteUiStateEdit.noteDetails.tipo=optionNote
            notesRepository.updateNote(noteUiStateEdit.noteDetails.toNoteEdit())
            actualizarImagenes()
            actualizarVideos()
        }
    }

    suspend fun actualizarImagenes(){
        notesRepository.deleteAllImages(itemId)
        urislist.forEach{uri->
            var imageNota=ImageNotaEntity(0, itemId,""+uri)
            notesRepository.insertImage(imageNota)
        }
    }

    suspend fun actualizarVideos(){
        notesRepository.deleteAllVideos(itemId)
        urislist.forEach{uri->
            var videoNota= VideoNotaEntity(0, itemId,""+uri)
            notesRepository.insertVideo(videoNota)
        }
    }

    private fun validateInputEdit(uiState: NoteDetailsEdit = noteUiStateEdit.noteDetails): Boolean {
        return with(uiState) {
            titulo.isNotBlank() && contenido.isNotBlank() && fecha.isNotBlank()
        }
    }

    var isExpanded by mutableStateOf(false)
    var showCancelEdit by mutableStateOf(false)
    var recordatorios by mutableStateOf(false)

    fun updateShowCancelEdit(boolean: Boolean){
        showCancelEdit= boolean
    }

    fun updateIsExpandedEdit(boolean: Boolean){
        isExpanded= boolean
    }

    fun updateOptionNoteEdit(text: String ){
        optionNote=text
    }

    fun updateRecordatorios(boolean: Boolean){
        recordatorios= boolean
    }
    //IMAGENES
    var hasImage by mutableStateOf(false)
    var isEditar by mutableStateOf(true)
    var mostrarImagen by mutableStateOf(false)
    var cantidad by mutableStateOf(0)
    var imageUri by mutableStateOf<Uri?>(null)

    //VIDEOS
    var hasVideo by mutableStateOf(false)
    var mostrarVideo by mutableStateOf(false)
    var cantidadVideos by mutableStateOf(0)
    var videoUri by mutableStateOf<Uri?>(null)
    //AUDIOS
    var hasAudio by mutableStateOf(false)
    var cantidadAudios by mutableStateOf(0)
    var audioUri by mutableStateOf<Uri?>(null)
    var urisAudioslist= mutableStateListOf<Uri>()

    //IMAGENES
    fun updatehasImage(boolean: Boolean){
        hasImage= boolean
    }

    fun updateIsEditar(boolean: Boolean){
        isEditar=boolean
    }

    fun updateImageUri(uri: Uri?){
        imageUri= uri
    }

    fun updateUrisList(uri: Uri?){
        urislist.add(uri)
        cantidad=urislist.size
    }

    fun updateCantidad(){
        cantidad=urislist.size
    }

    fun deleteLastUri(){
        urislist.removeLast()
        cantidad=urislist.size
    }

    fun updateMostrarImagen(boolean: Boolean){
        mostrarImagen= boolean
    }

    //VIDEOS
    fun updatehasVideo(boolean: Boolean){
        hasVideo= boolean
    }

    fun updateVideoUri(uri: Uri?){
        videoUri= uri
    }

    fun updateUrisVideosList(uri: Uri){
        urisVideoslist.add(uri)
        cantidadVideos=urisVideoslist.size
    }

    fun updateCantidadVideos(){
        cantidadVideos=urisVideoslist.size
    }

    fun deleteLastUriVideos(){
        urisVideoslist.removeLast()
        cantidadVideos=urisVideoslist.size
    }

    fun updateMostrarVideo(boolean: Boolean){
        mostrarVideo= boolean
    }

    //AUDIOS
    fun updatehasAudio(boolean: Boolean){
        hasAudio= boolean
    }

    fun updateAudioUri(uri: Uri?){
        audioUri= uri
    }

    fun updateUrisAudiosList(uri: Uri){
        urisAudioslist.add(uri)
        cantidadAudios=urisAudioslist.size
    }

    fun deleteLastUriAudios(){
        urisAudioslist.removeLast()
        cantidadAudios=urisAudioslist.size
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
    var tipo:String=""
)

fun NoteDetailsEdit.toNoteEdit(): NotaEntity = NotaEntity(
    id = id,
    titulo = titulo,
    contenido = contenido,
    fecha = fecha,
    tipo=tipo
)

fun NotaEntity.toNoteUiStateEdit(isEntryValid: Boolean = false): NoteUiStateEdit = NoteUiStateEdit(
    noteDetails = this.toNoteDetailsEdit(),
    isEntryValid = isEntryValid
)

fun NotaEntity.toNoteDetailsEdit(): NoteDetailsEdit = NoteDetailsEdit(
    id = id,
    titulo = titulo,
    contenido = contenido,
    fecha = fecha,
    tipo= tipo
)

//IMAGENES
/*
data class ImageEdit(
    val imageNotaEntity: ImageDetails
)

data class ImageDetails(
    var idNota: Int = 0,
    var uriImagen: String = ""
)

fun NotaEntity.toImageUris():ImageNotaEntity=ImageNotaEntity(){

}
fun ImageNotaEntity.toImage():ImageNotaEntity=ImageNotaEntity(
    idNota=idNota,
    uriImagen = uriImagen
)

fun ImageNotaEntity.toImageDetails():ImageDetails= ImageDetails(
    idNota=idNota,
    uriImagen = uriImagen
)*/