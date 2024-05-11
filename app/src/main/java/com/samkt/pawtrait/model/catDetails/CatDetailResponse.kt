package com.samkt.pawtrait.model.catDetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatDetailResponse(
    @SerialName("breeds")
    val breeds: List<Breed>,
    @SerialName("height")
    val height: Int,
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int
)