package dev.babananick.userstask.feature.userlist

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val userListModule = module {
    viewModelOf(::UserListViewModel)
}