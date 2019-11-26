package com.example.mvvm_skeleton

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set adapter to recyclerView
        val noteAdapter = NoteAdapter()
        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = noteAdapter

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNote()?.observe(this, Observer {
            noteAdapter.setNotes(it)
        })
    }

}
