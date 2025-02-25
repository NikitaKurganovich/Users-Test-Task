package dev.babananick.userstask.feature.userlist.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface UserListUiEvent {
    data class NavigateToUserDetails(val id: Int) : UserListUiEvent
    data object UpdateList: UserListUiEvent
    data object Retry: UserListUiEvent
}
