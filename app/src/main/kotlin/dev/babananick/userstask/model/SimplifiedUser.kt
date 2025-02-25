package dev.babananick.userstask.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class SimplifiedUser(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val email: String,
)
