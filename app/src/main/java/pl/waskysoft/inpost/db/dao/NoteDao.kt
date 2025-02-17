package pl.waskysoft.inpost.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.waskysoft.inpost.db.entity.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun loadAll(): Flow<List<Note>>

    @Insert
    suspend fun insertAll(vararg notes: Note)

    @Delete
    suspend fun delete(note: Note)

}