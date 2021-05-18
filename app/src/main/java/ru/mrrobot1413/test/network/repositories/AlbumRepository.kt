package ru.mrrobot1413.test.network.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.mrrobot1413.test.App
import ru.mrrobot1413.test.network.models.Album
import ru.mrrobot1413.test.storage.repositories.AlbumDbRepository
import ru.mrrobot1413.test.ui.AlbumPagingSource

object AlbumRepository {
    private lateinit var instance: AlbumRepository
    private val app: App by lazy {
        App().getInstance()
    }

    fun getInstance(): AlbumRepository {
        instance = this
        app.initRetrofit()
        return instance
    }

    fun getAlbums(): Flow<PagingData<Album>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 5,
                initialLoadSize = 30,
                maxSize = 30,
                enablePlaceholders = true
            ), pagingSourceFactory = {
                AlbumPagingSource(api = app.api, AlbumDbRepository.getInstance())
            }).flow
    }
}