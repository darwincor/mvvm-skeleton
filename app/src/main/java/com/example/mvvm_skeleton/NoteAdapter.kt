package com.example.mvvm_skeleton

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var notes: List<Note> = emptyList()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.text_view_title)
        var description: TextView = view.findViewById(R.id.text_view_description)
        var priority: TextView = view.findViewById(R.id.text_view_priority)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    public fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note: Note = notes.get(position)
        holder.title.text = note.title
        holder.description.text = note.description
        holder.priority.text = note.priority.toString()
    }
}