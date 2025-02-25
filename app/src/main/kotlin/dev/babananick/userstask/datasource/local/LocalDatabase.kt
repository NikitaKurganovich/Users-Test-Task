package dev.babananick.userstask.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.babananick.userstask.model.SimplifiedUser
import dev.babananick.userstask.model.User

@Database(entities = [User::class, SimplifiedUser::class], version = 1)
@TypeConverters(
    AddressTypeConverter::class,
    GeoTypeConverter::class,
    CompanyTypeConverter::class
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}