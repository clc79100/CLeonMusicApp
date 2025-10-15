package com.example.musicapp.components
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.musicapp.models.Album

@Composable
fun Albums(albums: List<Album>){
    LazyRow(
        modifier = Modifier.fillMaxSize()
    ) {
        items(albums){ album ->
            AlbumCard(album)
        }
    }
}