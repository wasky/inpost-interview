package pl.waskysoft.inpost.screen.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import pl.waskysoft.inpost.db.AppDatabase
import pl.waskysoft.inpost.db.entity.Note
import pl.waskysoft.inpost.di.IoDispatcher
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val db: AppDatabase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    fun addNote(text: String) = viewModelScope.launch(ioDispatcher) {
        val noteDao = db.noteDao()
        noteDao.insertAll(Note(text = text))
    }

    fun loadNotes() = db.noteDao().loadAll()

}