package ru.mrrobot1413.test.di.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mrrobot1413.test.App
import ru.mrrobot1413.test.network.Api
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(App.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesApi(): Api{
        return providesRetrofitBuilder().create(Api::class.java)
    }
}