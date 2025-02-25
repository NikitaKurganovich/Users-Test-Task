package dev.babananick.userstask.model

import androidx.annotation.StringRes
import dev.babananick.userstask.R

data class DataWithSource<D>(
    val data: D,
    val source: Source
){
    enum class Source(
        @StringRes val id: Int
    ){
        LOCAL(R.string.data_source_type_local),
        REMOTE(R.string.data_source_type_remote),
        NONE(R.string.data_source_type_none),
        FAKE(1)
    }
}