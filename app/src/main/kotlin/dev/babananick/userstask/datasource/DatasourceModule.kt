package dev.babananick.userstask.datasource

import android.content.Context
import androidx.room.Room
import dev.babananick.userstask.datasource.local.LocalDatabase
import dev.babananick.userstask.datasource.local.UserDao
import dev.babananick.userstask.datasource.remote.RemoteDatasource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val datasourceModule = module{
    singleOf(::RemoteDatasource)
    singleOf(::provideDatabase)
    singleOf(::provideUserDao)
}

fun provideDatabase(context: Context): LocalDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        LocalDatabase::class.java, "user-database"
    ).build()
}

fun provideUserDao(database: LocalDatabase): UserDao {
    return database.userDao()
}