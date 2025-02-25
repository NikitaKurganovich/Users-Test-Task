package dev.babananick.userstask.data

import dev.babananick.userstask.model.DataWithSource
import dev.babananick.userstask.model.SimplifiedUser
import dev.babananick.userstask.model.User

interface UserRepository {
    suspend fun getSimplifiedUsers(): Result<DataWithSource<List<SimplifiedUser>>>
    suspend fun getUserById(id: Int): Result<DataWithSource<User>>

    suspend fun saveUserDetails(user: User)
    suspend fun saveSimplifiedUsers(users: List<SimplifiedUser>)
}