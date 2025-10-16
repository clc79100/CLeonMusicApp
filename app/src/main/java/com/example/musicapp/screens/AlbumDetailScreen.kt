package com.example.musicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.musicapp.components.Player
import com.example.musicapp.components.RecentlyPlayedAlbum
import com.example.musicapp.models.Album
import com.example.musicapp.services.AlbumService
import com.example.musicapp.ui.theme.Amethyst
import com.example.musicapp.ui.theme.DarkPurlple
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.background
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun AlbumDetailScreen(
    innerPaddingValues: PaddingValues,
    navController: NavController,
    id: String
){
    var album by remember {
        mutableStateOf<Album?>(null)
    }

    var loading by remember {
        mutableStateOf(true)
    }

    var songs by remember {
        mutableStateOf(listOf<Album?>())
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
            songs = List(10) { song -> album }
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
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(color = Amethyst)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)

            ) {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                        .clip(RoundedCornerShape(24.dp)),
                ){
                    AsyncImage(
                        model = album?.image,
                        contentScale = ContentScale.Crop,
                        contentDescription = album?.title ?: "error",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Amethyst)

                    )
                    //Scrim
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Amethyst.copy(alpha = 0.25f))
                    )

                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp, vertical = 12.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ){
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(55.dp)
                                    .clip(CircleShape)
                                    .background(Color(0x60000000))
                                    .padding(15.dp)
                                    .clickable{navController.popBackStack()}
                            )
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(55.dp)
                                    .clip(CircleShape)
                                    .background(Color(0x60000000))
                                    .padding(15.dp)
                            )
                        }
                        Column {
                            Text(
                                text = album?.title ?: "error",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = album?.artist ?: "error",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 9.dp)
                            )

                            Row {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(end = 12.dp)
                                        .size(55.dp)
                                        .clip(CircleShape)
                                        .background(Amethyst)
                                        .padding(15.dp)
                                )
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = DarkPurlple,
                                    modifier = Modifier
                                        .size(55.dp)
                                        .clip(CircleShape)
                                        .background(Color.White)
                                        .padding(15.dp)
                                )
                            }
                        }
                    }
                }


                Column (
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .shadow(6.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .height(90.dp)
                        .background(Color.White)
                        .padding(12.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = "About this Album",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkPurlple,
                    )

                    Text(
                        text = album?.description ?: "error",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        lineHeight = 16.sp
                    )
                }

                Text(
                    text = "Artist" + album?.artist,
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(3.dp)
                )

                LazyColumn (modifier = Modifier.padding(top = 4.dp)){
                    itemsIndexed(songs){ index,song ->
                        RecentlyPlayedAlbum(
                            album= song,
                            titleText = " • Track ${index+1}",
                            onClick= {}
                        )
                    }
                }
            }
            Player(album)
        }
    }
}

@Preview
@Composable
fun AlbumDetailScreenPreview(){
    MusicAppTheme {
        AlbumDetailScreen(PaddingValues(0.dp), rememberNavController(), "")
    }
}