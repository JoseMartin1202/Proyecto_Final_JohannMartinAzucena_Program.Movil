package com.example.proyectofinal_jma.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Nota::class], version = 1, exportSchema = false)
abstract class InventoryDatabase:RoomDatabase() {

    abstract fun notaDAO():NotaDAO

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "database_notes")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}