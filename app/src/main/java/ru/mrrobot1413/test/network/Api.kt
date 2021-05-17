package ru.mrrobot1413.test.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import ru.mrrobot1413.test.network.models.Album
import ru.mrrobot1413.test.network.models.AlbumResponse

interface Api {

    @GET("albums")
    fun getAlbums() : Deferred<List<Album>>
}