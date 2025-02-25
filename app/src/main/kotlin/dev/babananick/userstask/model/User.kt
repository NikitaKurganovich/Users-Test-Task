package dev.babananick.userstask.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company,
){
    constructor() : this(
        id = 0,
        name = "",
        username = "",
        email = "",
        address = Address(),
        phone = "",
        website = "",
        company = Company()
    )
}