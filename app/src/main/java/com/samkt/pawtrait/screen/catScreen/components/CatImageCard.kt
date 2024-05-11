package com.samkt.pawtrait.screen.catScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CatImageCard(
    modifier: Modifier = Modifier,
    url: String = "",
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    AsyncImage(
        modifier =
            modifier
                .clickable {
                    onClick()
                }
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(204.dp)
                .clip(RoundedCornerShape(8.dp)),
        model =
            ImageRequest.Builder(context)
                .data(url)
                .crossfade(true)
                .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}
