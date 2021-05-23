package ru.mrrobot1413.test.storage.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mrrobot1413.test.App
import ru.mrrobot1413.test.di.AppComponentSource
import ru.mrrobot1413.test.di.AppComponentSource.Companion.appComponentSource
import ru.mrrobot1413.test.storage.AlbumDao
import ru.mrrobot1413.test.storage.models.AlbumDb
import javax.inject.Inject

class AlbumDbRepository @Inject constructor(albumDaoSource: AlbumDao) {
    private val albumDao = albumDaoSource

    init {
        appComponentSource.inject(this)
    }

    suspend fun selectAll(): List<AlbumDb> {
        return withContext(Dispatchers.IO) { albumDao.selectAll() }
    }

    suspend fun insert(list: List<AlbumDb>) {
        withContext(Dispatchers.IO) {
            albumDao.insert(list)
        }
    }
}