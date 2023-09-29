package com.example.proyectofinal_jma.data

import com.example.proyectofinal_jma.R
import com.example.proyectofinal_jma.model.Homework
import com.example.proyectofinal_jma.model.Note

object DataSourceNotesOrHomework {
    val cardsHomeworksAndNotes= listOf(
        Homework(R.drawable.image,R.string.title,R.string.tareaDescripcion,R.string.date,R.drawable.star,R.drawable.bell_outlined),
        Homework(R.drawable.image,R.string.title,R.string.tareaDescripcion,R.string.date,R.drawable.star,R.drawable.bell_outlined),
        Note(R.drawable.image,R.string.title,R.string.notaDescripcion,R.string.date,R.drawable.star),
        Note(R.drawable.image,R.string.title,R.string.notaDescripcion,R.string.date,R.drawable.star),
        Homework(R.drawable.image,R.string.title,R.string.tareaDescripcion,R.string.date,R.drawable.star,R.drawable.bell_outlined),
        Note(R.drawable.image,R.string.title,R.string.notaDescripcion,R.string.date,R.drawable.star)
    )
}