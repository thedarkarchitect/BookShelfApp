package com.example.bookshelf.data

import com.example.bookshelf.network.BookApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppContainer {
    val bookApiService: BookApiService
    val bookShelfRepository: BookShelfRepository
}

class DefaultAppContainer : AppContainer {

    override val bookApiService: BookApiService by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BookApiService.BASE_URL)
            .build()
            .create()
    }

    override val bookShelfRepository: BookShelfRepository by lazy {
        NetworkBookShelfRepository(bookApiService)
    }
}