package com.example.proyectofinal_jma.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}