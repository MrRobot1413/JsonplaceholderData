package ru.mrrobot1413.test.ui

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.mrrobot1413.test.network.Api
import ru.mrrobot1413.test.network.models.Album
import java.io.IOException

class AlbumPagingSource(
    private val api: Api
) : PagingSource<Int, Album>
    () {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        val position = params.key ?: 10

        return try {
            val response =
                api.getAlbums().await()

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            Log.d("Exceptions", "IOException: ${e.message}")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.d("Exceptions", "HttpException: ${e.message}")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}