package com.example.musicapp.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.musicapp.models.Album
import com.example.musicapp.routes.AlbumDetailScreenRoute

@Composable
fun Albums(albums: List<Album>, navController: NavController){
    LazyRow(
        modifier = Modifier
            .fillMaxSize()

    ) {
        items(albums){ album ->
            AlbumCard(
                album = album,
                onClick = {
                    navController.navigate(AlbumDetailScreenRoute(album.id))
                }
            )
        }
    }
}