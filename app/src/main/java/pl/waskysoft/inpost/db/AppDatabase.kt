package pl.waskysoft.inpost.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.waskysoft.inpost.db.dao.NoteDao
import pl.waskysoft.inpost.db.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}