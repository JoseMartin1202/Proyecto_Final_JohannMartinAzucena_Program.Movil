package com.example.proyectofinal_jma.data

import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    /** Retorna todos las notas de la fuente de datos dada.*/
    fun getAllNotesStream(): Flow<List<Nota>>
    /** Retorna una note de la fuente de datos dada que concide con el id entregado*/
    fun getNoteStream(id: Int): Flow<Nota?>
    /** Insertar una nota en la fuente de datos*/
    suspend fun insertNote(nota: Nota)
    /**Borrar nota de la fuente de datos*/
    suspend fun deleteNote(nota: Nota)
    /**Actualizar nota de la fuente de datos**/
    suspend fun updateNote(nota: Nota)
}