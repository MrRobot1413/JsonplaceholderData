package ru.mrrobot1413.test.di.modules

import android.app.Application
import androidx.room.Room
import androidx.room.RoomMasterTable.TABLE_NAME
import dagger.Module
import dagger.Provides
import ru.mrrobot1413.test.App
import ru.mrrobot1413.test.storage.AlbumDao
import ru.mrrobot1413.test.storage.AppDatabase
import ru.mrrobot1413.test.storage.repositories.AlbumDbRepository
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {
    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            App.TABLE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase{
        return db
    }

    @Singleton
    @Provides
    fun providesDao(database: AppDatabase): AlbumDao{
        return database.albumDao()
    }
}