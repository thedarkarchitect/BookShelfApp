package com.example.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BookShelfRepository
import com.example.bookshelf.model.BookShelf
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface BookUiState {
    data class Success(val books: List<BookShelf>?): BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}


class BookShelfViewModel(private val bookShelfRepository: BookShelfRepository) : ViewModel(){

    var bookUiState : BookUiState by mutableStateOf(BookUiState.Loading)
        private set


    init {
        getBookShelf()
    }

    fun getBookShelf(){
        viewModelScope.launch {
            bookUiState = BookUiState.Loading
            bookUiState = try {
                val result = bookShelfRepository.getBooks("space")
                BookUiState.Success(result)
            }catch (e: HttpException){
                BookUiState.Error
            }catch (e: IOException){
                BookUiState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val bookShelfRepository = application.container.bookShelfRepository
                BookShelfViewModel(bookShelfRepository = bookShelfRepository)
            }
        }
    }

}