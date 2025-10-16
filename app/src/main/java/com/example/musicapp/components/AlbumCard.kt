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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
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
import com.example.musicapp.ui.theme.DarkPurlple
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.Purple40

@Composable
fun AlbumCard(album: Album, onClick: () -> Unit){
    Box (
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .width(180.dp)
            .height(170.dp)
            .padding(7.dp)
            .clickable{onClick()},
        contentAlignment = Alignment.BottomEnd
    ){
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .background(Purple40),
            model = album.image,
            contentScale = ContentScale.Crop,
            contentDescription = album.title
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .padding(bottom = 6.dp)
                .fillMaxWidth()
                .height(70.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(DarkPurlple.copy(alpha = 0.7f))
                .padding(horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ){
            Column()
            {
                Text(
                    text = album.title.take(15),
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = album.artist.take(15),
                    color = Color.LightGray,
                    fontSize = 10.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp)
            ){
                Icon(
                    imageVector = Icons.Rounded.PlayArrow,
                    contentDescription = null
                )
            }
        }
    }
}



@Preview
@Composable
fun AlbumCardPreview(){
    MusicAppTheme {
        AlbumCard(Album(
            "1234",
            "Hi This is Flume",
            "Flume",
            "Un Mixtape Experimental influenciado por el HyperPop, Wonky y Deconstructive Club",
            "https://upload.wikimedia.org/wikipedia/en/1/16/Flume_-_Hi_This_Is_Flume.png"
        ), { })
    }
}