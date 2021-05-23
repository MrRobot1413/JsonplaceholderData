package ru.mrrobot1413.test.network.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.mrrobot1413.test.di.AppComponentSource.Companion.appComponentSource
import ru.mrrobot1413.test.network.Api
import ru.mrrobot1413.test.network.models.Album
import ru.mrrobot1413.test.storage.repositories.AlbumDbRepository
import ru.mrrobot1413.test.ui.paging.AlbumPagingSource
import javax.inject.Inject

class AlbumRepository @Inject constructor(apiSource: Api, albumDbRepoSource: AlbumDbRepository) {
    private val api = apiSource
    private val albumDbRepo = albumDbRepoSource

    init {
        appComponentSource.inject(this)
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
                AlbumPagingSource(api = api, albumDbRepo)
            }).flow
    }
}