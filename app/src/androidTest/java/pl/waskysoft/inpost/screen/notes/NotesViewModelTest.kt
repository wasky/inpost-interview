@file:OptIn(ExperimentalCoroutinesApi::class)

package pl.waskysoft.inpost.screen.notes

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import pl.waskysoft.inpost.db.AppDatabase
import pl.waskysoft.inpost.notes.NotesLocalDataSource
import pl.waskysoft.inpost.notes.NotesRepository

class NotesViewModelTest {

    @Test
    fun addNote() = runTest {
        val dispatcher: CoroutineDispatcher = StandardTestDispatcher(testScheduler)
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        //val context = ApplicationProvider.getApplicationContext<Context>()
        val notesLocalDataSource = NotesLocalDataSource(db)
        val notesRepository = NotesRepository(notesLocalDataSource)
        val viewModel = NotesViewModel(dispatcher, notesRepository)

        assertEquals(true, viewModel.loadNotes().first().isEmpty())
        viewModel.addNote("text")
        advanceUntilIdle()
        val notes = viewModel.loadNotes().first()

        assertEquals(1, notes.size)
        assertEquals("text", notes.first().text)
    }

}