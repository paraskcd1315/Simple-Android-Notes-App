package com.paraskcd.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paraskcd.notes.models.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao
}