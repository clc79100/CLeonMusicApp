package com.example.musicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.components.Albums
import com.example.musicapp.components.Header
import com.example.musicapp.components.Player
import com.example.musicapp.components.RecentlyPlayed
import com.example.musicapp.components.TitleSection
import com.example.musicapp.models.Album
import com.example.musicapp.services.AlbumService
import com.example.musicapp.ui.theme.Amethyst
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.background
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun HomeScreen(
    innerPaddingValues: PaddingValues,
    navController: NavController
){
    var albums by remember {
        mutableStateOf(listOf<Album>())
    }

   var loading by remember {
       mutableStateOf(true)
    }

    LaunchedEffect(true) {
        try {
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://music.juanfrausto.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(AlbumService::class.java)
            val result = async (Dispatchers.IO){
                service.getAllAlbums()
            }
            Log.i("HomeScreen","${result.await()}")
            albums = result.await()
            loading = false
        } catch (e: Exception){
            Log.i("HomeScreen", e.toString())
        }
    }

    Box (
        modifier = Modifier
            .padding(innerPaddingValues)
            .background(background),
        contentAlignment = Alignment.Center
    ){
        if (loading){
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(color = Amethyst)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
            ) {
                Header(Modifier.weight(1f))

                Column (modifier = Modifier.weight(1f)) {
                    TitleSection("Albums")
                    Albums(albums, navController)
                }

                Column(modifier = Modifier.weight(2f)){
                    TitleSection("Recently Played")
                    RecentlyPlayed(albums, navController)

                }
            }
            Player(
                Album(
                "1234",
                "Hi This is Flume",
                "Flume",
                "Un Mixtape Experimental influenciado por el HyperPop, Wonky y Deconstructive Club",
                "https://cdn2.albumoftheyear.org/375x0/album/144918-hi-this-is-flume.jpg"
                )
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MusicAppTheme {
        HomeScreen(PaddingValues(0.dp), rememberNavController())
    }
}