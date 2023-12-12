package com.example.gestordepeliculas.ui.elements.videoplayer

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val player: Player,
    private val metaDataReader: MetaDataReader
): ViewModel() {

    private var _videoUri = MutableStateFlow<VideoItem>(VideoItem(contentUri = Uri.EMPTY, mediaItem = MediaItem.fromUri(Uri.EMPTY), name = metaDataReader.getMetaDataFromUri(Uri.EMPTY)?.fileName ?: "No name"))
    val videoItem: StateFlow<VideoItem> = _videoUri.asStateFlow()

    init {
        player.prepare()
    }

    fun addVideoUri(uri: Uri) {
        _videoUri.value = _videoUri.value.copy(
            contentUri = uri,
            mediaItem = MediaItem.fromUri(uri),
            name = metaDataReader.getMetaDataFromUri(uri)?.fileName ?: "No name"
        )
    }

    fun playVideo(uri: Uri) {
        player.setMediaItem(MediaItem.fromUri(uri))
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}