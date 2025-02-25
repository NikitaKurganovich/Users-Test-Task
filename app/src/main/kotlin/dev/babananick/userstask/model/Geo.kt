package dev.babananick.userstask.model

import kotlinx.serialization.Serializable

@Serializable
data class Geo(
    val lat: String,
    val lng: String
) {
    constructor() : this(
        lat = "",
        lng = ""
    )
}