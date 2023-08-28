package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.BookShelf

@Composable
fun HomeScreen(
    bookUiState: BookUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
){
    when(bookUiState){
        is BookUiState.Success -> bookUiState.books?.let { BookGrid(books = it) }//this lambda checks if the bookslist is not null and then passes it to the composable
        is BookUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is BookUiState.Error -> ErrorScreen(retryAction = retryAction , modifier = modifier.fillMaxSize())
    }
}

@Composable
fun BookGrid(books: List<BookShelf> ,modifier: Modifier =  Modifier){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp) )
    {
        items(items = books, key = { book -> book.id}){
            book ->
            BookCard(books = book)
        }
    }
}

@Composable
fun BookCard(
    books: BookShelf
){

    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(books.volumeInfo.imageLinks?.thumbnail)
            .crossfade(true)
            .build(),
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.loading_img),
        contentScale = ContentScale.Crop,
        contentDescription = ""
    )

}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading))
}

@Composable
fun ErrorScreen(retryAction:() -> Unit, modifier: Modifier = Modifier){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = "")
        Text(text = stringResource(id = R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction){
            Text(stringResource(id = R.string.retry))
        }
    }
}