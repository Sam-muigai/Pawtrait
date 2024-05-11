package com.samkt.pawtrait.data

import android.util.Log
import com.samkt.pawtrait.model.CatResponse
import com.samkt.pawtrait.model.catDetails.CatDetailResponse
import com.samkt.pawtrait.utils.Constants.API_KEY
import com.samkt.pawtrait.utils.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CatApiService(
    private val client: HttpClient,
) {
    suspend fun getCats(): List<CatResponse> {
        return client.get {
            url(urlString = HttpRoutes.getCats(10))
            parameter("breed_ids", "beng")
            parameter("api_key", API_KEY)
        }
            .bodyAsText()
            .let { json ->
                Json.decodeFromString(json)
            }
    }

    suspend fun getCatDetails(imageId: String): CatDetailResponse {
        return client.get(urlString = HttpRoutes.getCatDetails(imageId))
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
                            level = LogLevel.ALL
                            logger =
                                object : Logger {
                                    override fun log(message: String) {
                                        Log.d("", message)
                                    }
                                }
                        }

                        install(ContentNegotiation) {
                            json(
                                Json {
                                    prettyPrint = true
                                    isLenient = true
                                    useAlternativeNames = true
                                    ignoreUnknownKeys = true
                                    encodeDefaults = false
                                },
                            )
                        }
                    },
            )
        }
    }
}
