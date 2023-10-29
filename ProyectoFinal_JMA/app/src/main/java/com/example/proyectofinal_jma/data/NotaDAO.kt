package com.example.proyectofinal_jma.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDAO {
    @Insert
    suspend fun insert(nota:Nota)
    @Update
    suspend fun update(nota:Nota)
    @Delete
    suspend fun delete(nota:Nota)

    @Query("SELECT * from Notas WHERE id = :id")
    fun getItem(id: Int): Flow<Nota>

    @Query("SELECT * from Notas ORDER BY fecha ASC")
    fun getAllItems(): Flow<List<Nota>>
}