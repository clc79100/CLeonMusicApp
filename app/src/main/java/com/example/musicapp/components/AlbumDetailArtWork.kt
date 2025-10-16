package com.example.musicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.musicapp.models.Album
import com.example.musicapp.ui.theme.Amethyst
import com.example.musicapp.ui.theme.DarkPurlple
import com.example.musicapp.ui.theme.Pause

@Composable
fun AlbumDetailArtWork(navController: NavController, album: Album?){
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
                        imageVector = Pause,
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
}