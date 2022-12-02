package com.paraskcd.notes.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.paraskcd.notes.data.NotesDataSource
import com.paraskcd.notes.models.Note

class HomeViewModel: ViewModel() {
    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes(): List<Note> {
        return noteList
    }
}