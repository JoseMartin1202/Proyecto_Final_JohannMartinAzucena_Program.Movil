package com.example.proyectofinal_jma.record

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}