package com.example.mvvm_skeleton

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Entity (tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true) private var id: Int,
    private var title: String,
    private var description: String,
    private var priority: Int
)

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update (note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Note>>

}

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDo(): NoteDao

    companion object{
        private var instance: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase? {
            if (instance == null) {
                synchronized(NoteDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context,
                            NoteDatabase::class.java, "note_database")
                            .build()
                    }
                }
            }
            return instance
        }
    }
}
