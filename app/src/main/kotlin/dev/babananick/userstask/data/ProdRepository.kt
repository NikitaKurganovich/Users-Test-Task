package dev.babananick.userstask.data

import dev.babananick.userstask.datasource.local.LocalDatabase
import dev.babananick.userstask.datasource.remote.RemoteDatasource
import dev.babananick.userstask.model.DataWithSource
import dev.babananick.userstask.model.SimplifiedUser
import dev.babananick.userstask.model.User

class ProdRepository(
    private val remoteDataSource: RemoteDatasource,
    private val localDatasource: LocalDatabase
) : UserRepository {
    override suspend fun getSimplifiedUsers(): Result<DataWithSource<List<SimplifiedUser>>> =
        kotlin.runCatching {
            DataWithSource(
                remoteDataSource.fetchSimplifiedUsers(),
                DataWithSource.Source.REMOTE
            )
        }.onFailure {
            return kotlin.runCatching {
                DataWithSource(
                    localDatasource.userDao().getAllSimplifiedUsers()!!,
                    DataWithSource.Source.LOCAL
                )
            }
        }

    override suspend fun getUserById(id: Int): Result<DataWithSource<User>> = kotlin.runCatching {
        DataWithSource(
            remoteDataSource.fetchUserById(id),
            DataWithSource.Source.REMOTE
        )
    }.onFailure {
        return kotlin.runCatching {
            DataWithSource(
                localDatasource.userDao().getUserById(id)!!,
                DataWithSource.Source.LOCAL
            )
        }
    }

    override suspend fun saveUserDetails(user: User) {
        localDatasource.userDao().insertUser(user)
    }

    override suspend fun saveSimplifiedUsers(users: List<SimplifiedUser>) {
        localDatasource.userDao().insertSimplifiedUsers(*users.toTypedArray())
    }
}
