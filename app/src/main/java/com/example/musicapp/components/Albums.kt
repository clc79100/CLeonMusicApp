package com.example.musicapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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