package com.example.mvvm_skeleton

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

public class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: NoteRepository = NoteRepository(application)
    private var allNotes: LiveData<List<Note>>? = repository.getAllNotes()

    fun insertNote(note: Note) = repository.insertNote(note)
    fun updateNote(note: Note) = repository.updateNote(note)
    fun deleteNote(note: Note) = repository.deleteNote(note)
    fun deleteAllNotes() = repository.deleteAllNotes()
    fun getAllNote(): LiveData<List<Note>>? = repository.getAllNotes()
}