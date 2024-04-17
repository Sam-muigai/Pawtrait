package com.samkt.pawtrait.model.catDetails


import com.squareup.moshi.Json

data class CatDetailResponse(
    @Json(name = "breeds")
    val breeds: List<Breed>,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "width")
    val width: Int
)