package ru.mrrobot1413.test.network.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import ru.mrrobot1413.test.network.models.Album
import ru.mrrobot1413.test.network.repositories.AlbumRepository
import kotlin.Exception

class AlbumViewModel : ViewModel() {

    private val albumRepository = AlbumRepository.getInstance()

    private val _albums = MutableLiveData<PagingData<Album>>()
    val albums: LiveData<PagingData<Album>> = _albums

    fun getAlbums(){
        viewModelScope.launch {
            try {
                albumRepository.getAlbums().cachedIn(viewModelScope).collectLatest {
                    _albums.value = it
                }
            } catch (e: Exception){
                Log.d("Exceptions", e.message.toString())
            }
        }
    }
}