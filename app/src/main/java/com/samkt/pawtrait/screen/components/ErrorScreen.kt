package com.samkt.pawtrait.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String,
) {
    Box(
        modifier =
            modifier.fillMaxSize()
                .background(Color(0xFF7D7D7D)),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = errorMessage)
    }
}
