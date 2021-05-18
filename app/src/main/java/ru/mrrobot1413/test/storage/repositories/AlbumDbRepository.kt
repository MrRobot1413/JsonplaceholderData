package ru.mrrobot1413.test.storage.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mrrobot1413.test.App
import ru.mrrobot1413.test.storage.AlbumDao
import ru.mrrobot1413.test.storage.models.AlbumDb

object AlbumDbRepository {
    private lateinit var instance: AlbumDbRepository
    private val app: App by lazy {
        App().getInstance()
    }
    private val db by lazy {
        app.getDatabase()
    }
    private lateinit var albumDao: AlbumDao

    fun getInstance(): AlbumDbRepository {
        instance = this
        albumDao = db.albumDao()
        return instance
    }

    suspend fun selectAll(): List<AlbumDb>{
        return withContext(Dispatchers.IO){ albumDao.selectAll() }
    }

    suspend fun insert(list: List<AlbumDb>){
        withContext(Dispatchers.IO){
            albumDao.insert(list)
        }
    }
}