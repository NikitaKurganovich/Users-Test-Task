package dev.babananick.userstask.feature.userdetail

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val userDetailsModule = module {
    viewModelOf(::UserDetailsViewModel)
}