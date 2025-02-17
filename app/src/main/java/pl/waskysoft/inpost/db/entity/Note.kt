package pl.waskysoft.inpost.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "created") val created: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "text") val text: String
)