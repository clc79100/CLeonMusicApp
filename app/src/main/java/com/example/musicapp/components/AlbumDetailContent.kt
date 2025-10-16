package com.example.musicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicapp.models.Album
import com.example.musicapp.ui.theme.DarkPurlple

@Composable

fun AlbumDetailContent(album: Album?){
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
        buildAnnotatedString {
            withStyle(style = SpanStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DarkPurlple,
            )){
                append("Artist: ")
            }
            withStyle(style = SpanStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
            )){
                append(album?.artist ?: "error")
            }
        },
        modifier = Modifier
            .padding(vertical = 5.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}