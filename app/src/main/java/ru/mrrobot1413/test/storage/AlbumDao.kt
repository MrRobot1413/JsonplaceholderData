package ru.mrrobot1413.test.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mrrobot1413.test.storage.models.AlbumDb

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums")
    fun selectAll(): List<AlbumDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<AlbumDb>)
}