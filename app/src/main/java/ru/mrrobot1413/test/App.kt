package ru.mrrobot1413.test

import android.app.Application
import ru.mrrobot1413.test.di.AppComponent
import ru.mrrobot1413.test.di.AppComponentSource
import ru.mrrobot1413.test.di.DaggerAppComponent
import ru.mrrobot1413.test.di.modules.AppModule
import ru.mrrobot1413.test.di.modules.RepositoriesModule
import ru.mrrobot1413.test.di.modules.RetrofitModule
import ru.mrrobot1413.test.di.modules.RoomModule

class App : Application(){

    private val appComponent: AppComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .retrofitModule(RetrofitModule())
        .roomModule(RoomModule(this))
        .repositoriesModule(RepositoriesModule())
        .build()

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val TABLE_NAME = "albums"

        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppComponentSource.setAppComponent(appComponent)
    }
}