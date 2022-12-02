package com.paraskcd.notes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.paraskcd.notes.models.Note

@Dao
interface NoteDatabaseDao {
    @Query("SELECT * FROM notes_tbl")
    fun getNotes(): List<Note>

    @Query("SELECT * FROM notes_tbl WHERE id = :id")
    fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    @Query("DELETE FROM notes_tbl")
    fun deleteAll()

    @Delete
    fun deleteNote(note: Note)
}
