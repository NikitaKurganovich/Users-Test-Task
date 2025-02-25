package dev.babananick.userstask

import android.app.Application
import dev.babananick.userstask.data.dataModule
import dev.babananick.userstask.datasource.datasourceModule
import dev.babananick.userstask.feature.userdetail.userDetailsModule
import dev.babananick.userstask.feature.userlist.userListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UserTaskApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@UserTaskApplication)
            modules(
                dataModule,
                datasourceModule,
                userListModule,
                userDetailsModule
            )
        }
    }
}