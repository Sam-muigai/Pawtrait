package com.samkt.pawtrait.model.catDetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Breed(
    @SerialName("adaptability")
    val adaptability: Int,
    @SerialName("affection_level")
    val affectionLevel: Int,
    @SerialName("bidability")
    val bidability: Int,
    @SerialName("cat_friendly")
    val catFriendly: Int,
    @SerialName("cfa_url")
    val cfaUrl: String,
    @SerialName("child_friendly")
    val childFriendly: Int,
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("country_codes")
    val countryCodes: String,
    @SerialName("description")
    val description: String,
    @SerialName("dog_friendly")
    val dogFriendly: Int,
    @SerialName("energy_level")
    val energyLevel: Int,
    @SerialName("experimental")
    val experimental: Int,
    @SerialName("grooming")
    val grooming: Int,
    @SerialName("hairless")
    val hairless: Int,
    @SerialName("health_issues")
    val healthIssues: Int,
    @SerialName("hypoallergenic")
    val hypoallergenic: Int,
    @SerialName("id")
    val id: String,
    @SerialName("indoor")
    val indoor: Int,
    @SerialName("intelligence")
    val intelligence: Int,
    @SerialName("lap")
    val lap: Int,
    @SerialName("life_span")
    val lifeSpan: String,
    @SerialName("name")
    val name: String,
    @SerialName("natural")
    val natural: Int,
    @SerialName("origin")
    val origin: String,
    @SerialName("rare")
    val rare: Int,
    @SerialName("reference_image_id")
    val referenceImageId: String,
    @SerialName("rex")
    val rex: Int,
    @SerialName("shedding_level")
    val sheddingLevel: Int,
    @SerialName("short_legs")
    val shortLegs: Int,
    @SerialName("social_needs")
    val socialNeeds: Int,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int,
    @SerialName("suppressed_tail")
    val suppressedTail: Int,
    @SerialName("temperament")
    val temperament: String,
    @SerialName("vcahospitals_url")
    val vcahospitalsUrl: String,
    @SerialName("vetstreet_url")
    val vetstreetUrl: String,
    @SerialName("vocalisation")
    val vocalisation: Int,
    @SerialName("weight")
    val weight: Weight,
    @SerialName("wikipedia_url")
    val wikipediaUrl: String
)