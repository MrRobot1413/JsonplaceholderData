package ru.mrrobot1413.test.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mrrobot1413.test.storage.models.AlbumDb

@Database(entities = [AlbumDb::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun albumDao(): AlbumDao
}