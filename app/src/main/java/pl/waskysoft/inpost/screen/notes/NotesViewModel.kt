package pl.waskysoft.inpost.screen.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.waskysoft.inpost.db.AppDatabase
import pl.waskysoft.inpost.db.entity.Note

class DetailsViewModel(app: Application) : AndroidViewModel(app) {

    private val db get() = AppDatabase.getInstance(getApplication())

    fun addNote(text: String) = viewModelScope.launch(Dispatchers.IO) {
        val noteDao = db.noteDao()
        noteDao.insertAll(Note(text = text))
    }

    fun loadNotes() = db.noteDao().loadAll()

}