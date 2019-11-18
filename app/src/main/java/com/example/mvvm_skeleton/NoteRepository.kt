package com.example.mvvm_skeleton

import android.app.Application
import androidx.lifecycle.LiveData

class NoteRepository (application: Application){

    var noteDatabase = NoteDatabase.getDatabase(application)

    var noteDao: NoteDao? = noteDatabase?.noteDo()
    var mAllNotes: LiveData<List<Note>>? = noteDao?.getAllNotes()

    fun insertNote(note: Note) = noteDao?.insert(note)
    fun updateNote(note: Note) = noteDao?.update(note)
    fun deleteNote(note: Note) = noteDao?.delete(note)
    fun deleteAllNotes() = noteDao?.deleteAllNotes()
    fun getAllNotes(): LiveData<List<Note>>? = mAllNotes
}