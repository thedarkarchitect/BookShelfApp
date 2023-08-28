package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class BookShelf(
    val id: String,
    val volumeInfo: VolumeInfo,
)

@Serializable
data class VolumeInfo(
    val imageLinks: ImageLinks? =null
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
){
    val httpsThumbnail : String
        get() = thumbnail.replace("http", "https")
}



