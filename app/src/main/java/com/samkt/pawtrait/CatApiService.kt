package com.samkt.pawtrait

import android.util.Log
import com.samkt.pawtrait.model.CatResponse
import com.samkt.pawtrait.utils.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

class CatApiService(
    private val client: HttpClient
) {
    suspend fun getCats(): List<CatResponse> {
        return client.get(urlString = HttpRoutes.getCats(10))
            .bodyAsText()
            .let { json ->
                Json.decodeFromString(json)
            }
    }


    companion object {
        fun getClient(): CatApiService {
            return CatApiService(
                client = HttpClient() {
                    install(Logging) {
                        logger = object : Logger {
                            override fun log(message: String) {
                                Log.d("", message)
                            }
                        }
                    }

                    install(ContentNegotiation) {
                        json()
                    }
                }
            )
        }
    }
}