package ru.mrrobot1413.test.ui.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.mrrobot1413.test.network.Api
import ru.mrrobot1413.test.network.models.Album
import ru.mrrobot1413.test.storage.models.AlbumDb
import ru.mrrobot1413.test.storage.repositories.AlbumDbRepository
import java.lang.Exception

class AlbumPagingSource(
    private val api: Api,
    dbRepository: AlbumDbRepository
) : PagingSource<Int, Album>() {

    private val albumDbRepository = dbRepository

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Album> {
        val position = params.key ?: 1

        return try {
            val dataList =
                api.getAlbums().await()

            val sortedList = dataList.sortedBy {
                it.title
            }

            val listToDb = ArrayList<AlbumDb>()

            sortedList.forEach {
                listToDb.add(
                    AlbumDb(
                        it.userId,
                        it.id,
                        it.title
                    )
                )
            }
            albumDbRepository.insert(listToDb)

            LoadResult.Page(
                data = sortedList,
                prevKey = null,
                nextKey = if (position == 1) null else position + 1
            )
        } catch (e: Exception) {
            Log.d("Exceptions", "Exception: ${e.message}")
            val listFromDb = albumDbRepository.selectAll()
            val resultList: ArrayList<Album> = ArrayList()
            listFromDb.forEach {
                resultList.add(
                    Album(
                        it.userId,
                        it.id,
                        it.title
                    )
                )
            }
            LoadResult.Page(
                data = resultList,
                prevKey = null,
                nextKey = if (position == 1) null else position + 1
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Album>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}