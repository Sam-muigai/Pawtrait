package com.samkt.pawtrait.model.catDetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weight(
    @SerialName("imperial")
    val imperial: String,
    @SerialName("metric")
    val metric: String
)