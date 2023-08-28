package com.example.bookshelf

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.screens.BookShelfViewModel
import com.example.bookshelf.ui.screens.HomeScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfApp(modifier: Modifier = Modifier){
    Scaffold(
        modifier = modifier,
        topBar = { BookShelfTopAppBar()}
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val bookShelfViewModel : BookShelfViewModel = viewModel(factory = BookShelfViewModel.Factory)
            HomeScreen(
                bookUiState = bookShelfViewModel.bookUiState,
                retryAction = bookShelfViewModel::getBookShelf
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfTopAppBar(modifier : Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
               text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier
    )
}