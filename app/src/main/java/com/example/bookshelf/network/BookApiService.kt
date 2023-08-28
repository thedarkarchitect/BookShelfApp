package com.example.bookshelf.network

import com.example.bookshelf.model.BookShelf
import com.example.bookshelf.model.ImageLinks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService{
    companion object {
        const val BASE_URL = "https://www.googleapis.com/books/v1/"
    }

    @GET("volumnes")
    suspend fun getBooks(@Query("q") query: String): Response<com.example.bookshelf.model.Response>

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id: String): Response<BookShelf>
}