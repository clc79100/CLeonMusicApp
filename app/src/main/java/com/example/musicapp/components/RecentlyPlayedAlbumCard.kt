package com.example.musicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.musicapp.models.Album
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.Purple40

@Composable
fun RecentlyPlayedAlbum(album: Album?, onClick: () -> Unit, titleText: String = "",artistText: String = ""){
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(10.dp)
            .clickable{onClick()},
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .size(55.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Purple40),
            model = album?.image,
            contentScale = ContentScale.Crop,
            contentDescription = album?.title ?: "error"
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1f)
        ){
            Text(
                text = album?.title?.plus(titleText) ?: "error",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = album?.artist?.plus(artistText) ?: "error",
                color = Color.Gray,
                fontSize = 13.sp
            )
        }
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}


@Preview
@Composable
fun RecentlyPlayedAlbumCardPreview(){
    MusicAppTheme {
        RecentlyPlayedAlbum(
            Album(
            "1234",
            "Hi This is Flume",
            "Flume",
            "Un Mixtape Experimental influenciado por el HyperPop, Wonky y Deconstructive Club",
            "https://upload.wikimedia.org/wikipedia/en/1/16/Flume_-_Hi_This_Is_Flume.png"
        ), {})
    }
}