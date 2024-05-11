package com.samkt.pawtrait.screen.catScreen

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.samkt.pawtrait.model.CatResponse
import com.samkt.pawtrait.screen.catDetailScreen.CatDetailScreen
import com.samkt.pawtrait.screen.catScreen.components.CatImageCard

class CatListScreen : Screen {
    @Composable
    override fun Content() {
        val catScreenModel =
            rememberScreenModel {
                CatListScreenModel()
            }

        val navigator = LocalNavigator.currentOrThrow
        CatScreenList(
            screenModel = catScreenModel,
            navigator = navigator,
        )
    }
}

@Composable
fun CatScreenList(
    screenModel: CatListScreenModel,
    navigator: Navigator,
) {
    when (val state = screenModel.catListScreenState.collectAsState().value) {
        is CatListScreenState.Error -> {
            ErrorScreen(errorMessage = state.message)
        }

        CatListScreenState.Loading -> {
            LoadingScreen()
        }

        is CatListScreenState.Success -> {
            CatListScreenContent(
                cats = state.cats,
                onCatClicked = { imageId ->
                    navigator.push(CatDetailScreen(imageId))
                },
            )
        }
    }
}

@Composable
fun CatListScreenContent(
    modifier: Modifier = Modifier,
    cats: List<CatResponse>,
    onCatClicked: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                content = {
                    item {
                        Column(
                            modifier = Modifier.padding(top = 22.dp, start = 20.dp),
                        ) {
                            Text(
                                text = "Pawtrait",
                                fontSize = 36.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                    items(cats) {
                        CatImageCard(
                            url = it.url,
                            onClick = {
                                onCatClicked.invoke(it.id)
                            },
                        )
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
