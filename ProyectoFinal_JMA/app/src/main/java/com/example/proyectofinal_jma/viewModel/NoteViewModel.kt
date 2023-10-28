package com.example.proyectofinal_jma.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.proyectofinal_jma.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteViewModel:ViewModel() {
    var textBodyNoteUser by mutableStateOf("")
    var textTitleNoteUser by mutableStateOf("")
    var optionNote by mutableStateOf("Nota")
    var sizeText by mutableStateOf("Normal")
    var isExpanded by mutableStateOf(false)
    var isExpanded2 by mutableStateOf(false)

    fun updateBodyNote(text: String ){
        textBodyNoteUser=text
    }

    fun updateTitleNote(text: String ){
        textTitleNoteUser=text
    }

    fun updateIsExpanden(boolean: Boolean){
        isExpanded= boolean
    }

    fun updateOptionNote(text: String ){
        optionNote=text
    }

    fun updateIsExpandend2(boolean: Boolean){
        isExpanded2= boolean
    }

    fun updatesizeText(text: String ){
        sizeText=text
    }
}