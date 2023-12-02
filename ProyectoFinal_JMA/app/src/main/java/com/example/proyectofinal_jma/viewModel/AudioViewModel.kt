package com.example.proyectofinal_jma.viewModel

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import com.example.proyectofinal_jma.playback.AndroidAudioPlayer
import com.example.proyectofinal_jma.record.AndroidAudioRecorder
import java.io.File

class AudioViewModel(application: Application) : AndroidViewModel(application) {
    val recorder by lazy { AndroidAudioRecorder(application.applicationContext) }
    val player by lazy { AndroidAudioPlayer(application.applicationContext) }
    var audioFile: File? = null
}