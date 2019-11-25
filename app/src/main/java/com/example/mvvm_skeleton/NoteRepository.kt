package com.example.mvvm_skeleton

import android.app.Application
import androidx.lifecycle.LiveData
import org.jetbrains.anko.doAsync

class NoteRepository (application: Application){

    var noteDatabase = NoteDatabase.getDatabase(application)

    var noteDao: NoteDao? = noteDatabase?.noteDao()
    var mAllNotes: LiveData<List<Note>>? = noteDao?.getAllNotes()

    fun insertNote(note: Note) = doAsync { noteDao?.insert(note) }
    fun updateNote(note: Note) = doAsync { noteDao?.update(note) }
    fun deleteNote(note: Note) = doAsync { noteDao?.delete(note) }
    fun deleteAllNotes() = doAsync { noteDao?.deleteAllNotes() }
    fun getAllNotes(): LiveData<List<Note>>? = mAllNotes
}