package ru.mrrobot1413.test.di.modules

import dagger.Module
import dagger.Provides
import ru.mrrobot1413.test.network.Api
import ru.mrrobot1413.test.network.repositories.AlbumRepository
import ru.mrrobot1413.test.storage.AlbumDao
import ru.mrrobot1413.test.storage.repositories.AlbumDbRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {
    @Singleton
    @Provides
    fun providesAlbumRepo(api: Api, albumDbRepo: AlbumDbRepository): AlbumRepository {
        return AlbumRepository(api, albumDbRepo)
    }

    @Singleton
    @Provides
    fun providesAlbumDbRepo(albumDao: AlbumDao): AlbumDbRepository {
        return AlbumDbRepository(albumDao)
    }
}