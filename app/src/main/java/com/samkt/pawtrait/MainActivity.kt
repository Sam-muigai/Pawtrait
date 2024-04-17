package com.samkt.pawtrait

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.samkt.pawtrait.model.CatResponse
import com.samkt.pawtrait.ui.theme.PawtraitTheme

class MainActivity : ComponentActivity() {

    private val service = CatApiService.getClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val cats = produceState<List<CatResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getCats()
                }
            )
            PawtraitTheme {
                LazyColumn {
                    items(cats.value) {
                        Text(text = it.url, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

