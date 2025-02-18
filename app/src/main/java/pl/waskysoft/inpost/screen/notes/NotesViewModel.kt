package pl.waskysoft.inpost.screen.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import pl.waskysoft.inpost.di.IoDispatcher
import pl.waskysoft.inpost.notes.NotesRepository
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val notesRepository: NotesRepository
) : ViewModel() {

    fun addNote(text: String) = viewModelScope.launch(ioDispatcher) {
        notesRepository.addNote(text)
    }

    fun loadNotes() = notesRepository.loadNotes()

}