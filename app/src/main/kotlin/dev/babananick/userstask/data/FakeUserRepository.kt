package dev.babananick.userstask.data

import dev.babananick.userstask.model.DataWithSource
import dev.babananick.userstask.model.SimplifiedUser
import dev.babananick.userstask.model.User

class FakeUserRepository : UserRepository {
    private val users = mutableListOf(
        User().copy(
            id = 1,
            name = "John Doe",
            email = "john.doe@example.com",
            username = "johndoe"
        ),
        User().copy(
            id = 2,
            name = "Jane Smith",
            email = "jane.smith@example.com",
            username = "janesmith"
        )
    )

    private val simplifiedUsers = users.map {
        SimplifiedUser(id = it.id, name = it.name, username = it.username, email = it.email)
    }.toMutableList()

    override suspend fun getSimplifiedUsers(): Result<DataWithSource<List<SimplifiedUser>>> =
        kotlin.runCatching {
            DataWithSource(simplifiedUsers.toList(), DataWithSource.Source.FAKE)
        }


    override suspend fun getUserById(id: Int): Result<DataWithSource<User>> = kotlin.runCatching {
        val user = users.find { it.id == id }
        if (user != null) {
            DataWithSource(user, DataWithSource.Source.FAKE)
        } else error("No such user")
    }

    override suspend fun saveUserDetails(user: User) {
        val existingUser = users.find { it.id == user.id }
        if (existingUser != null) {
            users.remove(existingUser)
        }
        users.add(user)
    }

    override suspend fun saveSimplifiedUsers(users: List<SimplifiedUser>) {
        this.simplifiedUsers.addAll(users)
    }
}

