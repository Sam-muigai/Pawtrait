package com.samkt.pawtrait.screen.catScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.samkt.pawtrait.model.CatResponse

class CatListScreen : Screen {
    @Composable
    override fun Content() {
        val catScreenModel =
            rememberScreenModel {
                CatListScreenModel()
            }

        CatScreenList(screenModel = catScreenModel)
    }
}

@Composable
fun CatScreenList(screenModel: CatListScreenModel) {
    LaunchedEffect(
        key1 = Unit,
        block = {
            screenModel.getCats()
        },
    )

    when (val state = screenModel.catListScreenState.collectAsState().value) {
        is CatListScreenState.Error -> {
            ErrorScreen(errorMessage = state.message)
        }

        CatListScreenState.Loading -> {
            LoadingScreen()
        }

        is CatListScreenState.Success -> {
            CatListScreenContent(cats = state.cats)
        }
    }
}

@Composable
fun CatListScreenContent(
    modifier: Modifier = Modifier,
    cats: List<CatResponse>,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            LazyColumn(
                content = {
                    items(cats) {
                        Text(text = it.url)
                    }
                },
            )
        }
    }
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = errorMessage)
    }
}