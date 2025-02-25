package dev.babananick.userstask.feature.userlist.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface UserListSideEffect {
    data class NavigateToDetails(val id: Int): UserListSideEffect
}
