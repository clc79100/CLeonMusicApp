package com.example.musicapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.musicapp.models.Album

@Composable
fun RecentlyPlayed(albums: List<Album>){
    LazyColumn {
        items(albums){ album ->
            RecentlyPlayedAlbum(album)
        }
    }
}