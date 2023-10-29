package com.example.proyectofinal_jma.data

import kotlinx.coroutines.flow.Flow

class OfflineNotesRepository(private val notaDao: NotaDAO) : NotesRepository{
    override fun getAllNotesStream(): Flow<List<Nota>> = notaDao.getAllItems()

    override fun getNoteStream(id: Int): Flow<Nota?> = notaDao.getItem(id)

    override suspend fun insertNote(item: Nota) = notaDao.insert(item)

    override suspend fun deleteNote(item: Nota) = notaDao.delete(item)

    override suspend fun updateNote(item: Nota) = notaDao.update(item)
}