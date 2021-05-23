package ru.mrrobot1413.test.di

import android.app.Application
import dagger.Component
import retrofit2.Retrofit
import ru.mrrobot1413.test.di.modules.AppModule
import ru.mrrobot1413.test.di.modules.RepositoriesModule
import ru.mrrobot1413.test.di.modules.RetrofitModule
import ru.mrrobot1413.test.di.modules.RoomModule
import ru.mrrobot1413.test.network.Api
import ru.mrrobot1413.test.network.repositories.AlbumRepository
import ru.mrrobot1413.test.network.viewModels.AlbumViewModel
import ru.mrrobot1413.test.storage.AlbumDao
import ru.mrrobot1413.test.storage.AppDatabase
import ru.mrrobot1413.test.storage.repositories.AlbumDbRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, RoomModule::class, RepositoriesModule::class])
interface AppComponent {

    fun inject(albumRepository: AlbumRepository)

    fun inject(albumDbRepository: AlbumDbRepository)

    fun inject(albumViewModel: AlbumViewModel)

    fun application(): Application

    fun roomDatabase(): AppDatabase

    fun gifDao(): AlbumDao

    fun albumRepo(): AlbumRepository

    fun albumDbRepo(): AlbumDbRepository

    fun retrofitBuilder(): Retrofit

    fun api(): Api
}