package dev.babananick.userstask.feature.userlist.model

import androidx.compose.runtime.Immutable
import dev.babananick.userstask.model.DataWithSource
import dev.babananick.userstask.model.SimplifiedUser
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class UserListState(
    val isLoading: Boolean,
    val isRefreshing: Boolean,
    val userList: ImmutableList<SimplifiedUser>,
    val source: DataWithSource.Source
){
    constructor(): this(
        isLoading = true,
        isRefreshing = false,
        userList = persistentListOf(),
        source = DataWithSource.Source.NONE
    )
}
