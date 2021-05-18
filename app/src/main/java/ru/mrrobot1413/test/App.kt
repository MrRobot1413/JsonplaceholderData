package ru.mrrobot1413.test

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mrrobot1413.test.network.Api
import ru.mrrobot1413.test.storage.AppDatabase

class App : Application(){

    lateinit var api: Api
    lateinit var db: AppDatabase

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val TABLE_NAME = "albums"

        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            TABLE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getInstance(): App {
        return instance
    }

    fun getDatabase(): AppDatabase {
        return db
    }

    fun initRetrofit() {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            })
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .baseUrl(BASE_URL)
            .build()

        api = retrofit.create(Api::class.java)
    }
}