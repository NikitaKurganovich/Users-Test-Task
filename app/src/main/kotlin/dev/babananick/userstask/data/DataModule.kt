package dev.babananick.userstask.data

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::ProdRepository) bind UserRepository::class
}