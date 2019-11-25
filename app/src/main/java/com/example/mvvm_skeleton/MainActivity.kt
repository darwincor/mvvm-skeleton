package com.example.mvvm_skeleton

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    lateinit var noteViewModel: NoteViewModel
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNote()?.observe(this, Observer {
            //Do whatever with the notes :)
            toast("Hola amigos :)")
        })
    }

}
