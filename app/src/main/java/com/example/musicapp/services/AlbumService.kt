package com.example.musicapp.services

import com.example.musicapp.models.Album
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {
    @GET(value = "albums")
    suspend fun getAllAlbums() : List<Album>

    @GET("albums/{id}")
    suspend fun getAlbumById(@Path(value = "id") id: String) : Album
}