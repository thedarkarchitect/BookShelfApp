package com.example.bookshelf.data

import com.example.bookshelf.model.BookShelf
import com.example.bookshelf.model.ImageLinks
import com.example.bookshelf.network.BookApiService

interface BookShelfRepository {
    suspend fun getBooks(query : String): List<BookShelf>?

    suspend fun getBook(id: String): BookShelf?
}

class NetworkBookShelfRepository(
    private val bookApiService: BookApiService
): BookShelfRepository{
    override suspend fun getBooks(query: String): List<BookShelf>? {
        return try {
            val result = bookApiService.getBooks(query)
            if(result.isSuccessful){
                result.body()?.items ?: emptyList()
            } else {
                emptyList()
            }
        }catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getBook(id: String): BookShelf? {
        return try {
            val result = bookApiService.getBook(id)
            if (result.isSuccessful){
                result.body()
            }else {
                null
            }
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    }

}