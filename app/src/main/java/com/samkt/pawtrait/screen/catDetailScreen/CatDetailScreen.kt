package com.samkt.pawtrait.screen.catDetailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.samkt.pawtrait.model.catDetails.CatDetailResponse
import com.samkt.pawtrait.screen.catScreen.components.ErrorScreen
import com.samkt.pawtrait.screen.catScreen.components.LoadingScreen

data class CatDetailScreen(val imageId: String) : Screen {
    @Composable
    override fun Content() {
        val catDetailModel =
            rememberScreenModel {
                CatDetailModel()
            }
        CatDetailScreen(
            screenModel = catDetailModel,
            imageId = imageId,
        )
    }
}

@Composable
fun CatDetailScreen(
    screenModel: CatDetailModel,
    imageId: String,
) {
    LaunchedEffect(
        key1 = Unit,
        block = {
            screenModel.getCatDetails(imageId)
        },
    )

    when (val state = screenModel.catDetailScreenState.collectAsState().value) {
        is CatDetailScreenState.Error -> {
            ErrorScreen(errorMessage = state.message)
        }

        CatDetailScreenState.Loading -> {
            LoadingScreen()
        }

        is CatDetailScreenState.Success -> {
            CartDetailScreenContent(cat = state.cats!!)
        }
    }
}

@Composable
fun CartDetailScreenContent(
    modifier: Modifier = Modifier,
    cat: CatDetailResponse,
) {
    val context = LocalContext.current
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            AsyncImage(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(320.dp),
                model =
                    ImageRequest.Builder(context)
                        .data(cat.url)
                        .crossfade(true)
                        .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
    }
}
