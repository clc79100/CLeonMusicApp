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
import com.example.musicapp.components.Player
import com.example.musicapp.models.Album
import com.example.musicapp.services.AlbumService
import com.example.musicapp.ui.theme.Amethyst
import com.example.musicapp.ui.theme.background
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AlbumDetailScreen(
    innerPaddingValues: PaddingValues,
    id: String
){
    var album by remember {
        mutableStateOf<Album?>(null)
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
            val result = withContext (Dispatchers.IO){
                service.getAlbumById(id)
            }
            album = result
            loading = false
            Log.i("AlbumDetailScreen", album.toString())
        } catch (e: Exception){
            Log.i("AlbumDetailScreen", e.toString())
        }
    }

    Box (
        modifier = Modifier
            .padding(innerPaddingValues)
            .background(background),
        contentAlignment = Alignment.Center
    ){
        if (loading){
            Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
                CircularProgressIndicator(color = Amethyst)
            }
        } else {
            Column {

            }

            Player(album)
        }


    }


}