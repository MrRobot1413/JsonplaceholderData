package ru.mrrobot1413.test.network.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mrrobot1413.test.di.AppComponentSource
import ru.mrrobot1413.test.di.AppComponentSource.Companion.appComponentSource
import ru.mrrobot1413.test.network.models.Album
import ru.mrrobot1413.test.network.repositories.AlbumRepository
import javax.inject.Inject
import kotlin.Exception

class AlbumViewModel : ViewModel() {

    @Inject
    lateinit var albumRepository: AlbumRepository

    private val _albums = MutableLiveData<PagingData<Album>>()
    val albums: LiveData<PagingData<Album>> = _albums

    init {
        appComponentSource.inject(this)
    }

    fun getAlbums(){
        viewModelScope.launch {
            try {
                albumRepository.getAlbums().cachedIn(viewModelScope).collectLatest { data ->
                    _albums.value = data
                }
            } catch (e: Exception){
                Log.d("Exceptions", e.message.toString())
            }
        }
    }
}