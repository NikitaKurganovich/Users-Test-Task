package dev.babananick.userstask.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.babananick.userstask.model.SimplifiedUser
import dev.babananick.userstask.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM SimplifiedUser")
    suspend fun getAllSimplifiedUsers(): List<SimplifiedUser>

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSimplifiedUsers(vararg users: SimplifiedUser)
}
