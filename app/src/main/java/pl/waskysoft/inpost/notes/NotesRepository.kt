package pl.waskysoft.inpost.notes

import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesLocalDataSource: NotesLocalDataSource) {

    suspend fun addNote(text: String) {
        notesLocalDataSource.addNote(text)
    }

    fun loadNotes() = notesLocalDataSource.loadNotes()

}