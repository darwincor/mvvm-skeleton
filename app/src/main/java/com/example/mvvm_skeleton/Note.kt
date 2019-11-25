package com.example.mvvm_skeleton

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import org.jetbrains.anko.doAsync

@Entity (tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true) var id: Int,
    var title: String,
    var description: String,
    var priority: Int
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

    abstract fun noteDao(): NoteDao

    companion object{
        private var instance: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase? {
            if (instance == null) {
                synchronized(NoteDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context,
                            NoteDatabase::class.java, "note_database")
                            .addCallback(object : Callback(){
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    //Moving to a new thread
                                    doAsync {
                                        populateDatabase(instance!!)
                                    }
                                }
                            })
                            .build()
                    }
                }
            }
            return instance
        }

        fun populateDatabase(db: NoteDatabase){
            val noteDao = db.noteDao()

            noteDao.insert(Note(1, "Title 1", "Description 2", 1))
            noteDao.insert(Note(2, "Title 2", "Description 2", 1))
            noteDao.insert(Note(3, "Title 3", "Description 2", 1))
        }

    }
}
