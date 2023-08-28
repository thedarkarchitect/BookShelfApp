package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val items: List<BookShelf>?,
    val totalItems: Int,
    val kind: String
)

