package pl.waskysoft.inpost.notes

import pl.waskysoft.inpost.db.AppDatabase
import pl.waskysoft.inpost.db.entity.Note
import javax.inject.Inject

class NotesLocalDataSource @Inject constructor(private val db: AppDatabase) {

    suspend fun addNote(text: String) {
        val noteDao = db.noteDao()
        noteDao.insertAll(Note(text = text))
    }

    fun loadNotes() = db.noteDao().loadAll()

}