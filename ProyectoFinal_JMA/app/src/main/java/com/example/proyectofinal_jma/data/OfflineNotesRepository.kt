package com.example.proyectofinal_jma.data

import kotlinx.coroutines.flow.Flow

class OfflineNotesRepository(private val notaDao: NotaDAO) : NotesRepository{
    override fun getAllNotesStream(): Flow<List<NotaEntity>> = notaDao.getAllItems()
    override fun getNoteStream(id: Int): Flow<NotaEntity> = notaDao.getItem(id)
    override suspend fun insertNote(item: NotaEntity) = notaDao.insert(item)
    override suspend fun deleteNote(item: NotaEntity) = notaDao.delete(item)
    override suspend fun updateNote(item: NotaEntity) = notaDao.update(item)
}