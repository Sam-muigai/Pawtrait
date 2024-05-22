package com.samkt.pawtrait.screen.catScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.samkt.pawtrait.screen.components.CatImageCard
import com.samkt.pawtrait.screen.components.ErrorScreen
import com.samkt.pawtrait.screen.components.LoadingScreen

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
    Scaffold { paddingValues ->
        Column(
            modifier =
                Modifier
                    .background(Color(0xFF7D7D7D))
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp),
        ) {
            Text(
                text = "Pawtrait",
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(20.dp))
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
    }
}

@Composable
fun CatListScreenContent(
    modifier: Modifier = Modifier,
    cats: List<CatResponse>,
    onCatClicked: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        content = {
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
