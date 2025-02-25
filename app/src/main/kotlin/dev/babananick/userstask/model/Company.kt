package dev.babananick.userstask.model

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
){
    constructor() : this(
        name = "",
        catchPhrase = "",
        bs = ""
    )
}