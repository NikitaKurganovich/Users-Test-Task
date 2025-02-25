package dev.babananick.userstask.datasource.remote

import dev.babananick.userstask.Config
import dev.babananick.userstask.model.SimplifiedUser
import dev.babananick.userstask.model.User
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RemoteDatasource {
    suspend fun fetchSimplifiedUsers(): List<SimplifiedUser> {
        val client = HttpClient()
        val response: HttpResponse = client.get(Config.API_URL)
        val users: List<SimplifiedUser> = response.body()
        client.close()

        return users
    }

    suspend fun fetchUserById(id: Int): User {
        val client = HttpClient()
        val response: HttpResponse = client.get(Config.API_URL + "/$id")
        val user: User = response.body()
        client.close()

        return user
    }

    private fun HttpClient(): HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
}