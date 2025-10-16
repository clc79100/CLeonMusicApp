package com.example.musicapp.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.musicapp.models.Album
import com.example.musicapp.routes.AlbumDetailScreenRoute

@Composable
fun RecentlyPlayed(albums: List<Album>, navController: NavController){
    LazyColumn {
        items(albums){ album ->
            RecentlyPlayedAlbum(
                album= album,
                onClick= {
                    navController.navigate(AlbumDetailScreenRoute(album.id))
                }
            )
        }
    }
}