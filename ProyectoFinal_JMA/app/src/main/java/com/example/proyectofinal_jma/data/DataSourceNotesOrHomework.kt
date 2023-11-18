package com.example.proyectofinal_jma.data

import com.example.proyectofinal_jma.R
import com.example.proyectofinal_jma.model.Content
import com.example.proyectofinal_jma.model.Homework
import com.example.proyectofinal_jma.model.HomeworkNoteDone
import com.example.proyectofinal_jma.model.Note

object DataSourceNotesOrHomework {
    val notesHomeworksDone= listOf(
        HomeworkNoteDone(R.drawable.image,R.string.title,R.string.notaDescripcion,R.string.date),
        HomeworkNoteDone(R.drawable.image,R.string.title,R.string.tareaDescripcion,R.string.date),
        HomeworkNoteDone(R.drawable.image,R.string.title,R.string.notaDescripcion,R.string.date)
    )

    val text = listOf(
        Content(R.string.escribirTexto)
    )
}