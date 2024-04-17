package com.samkt.pawtrait.data

import android.util.Log
import com.samkt.pawtrait.model.CatResponse
import com.samkt.pawtrait.utils.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*

class CatApiService(
    private val client: HttpClient,
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
                client =
                    HttpClient {
                        install(Logging) {
                            logger =
                                object : Logger {
                                    override fun log(message: String) {
                                        Log.d("", message)
                                    }
                                }
                        }

                        install(ContentNegotiation) {
                            json()
                        }
                    },
            )
        }
    }
}
