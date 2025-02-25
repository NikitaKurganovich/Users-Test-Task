package dev.babananick.userstask.feature.userdetail.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface UserDetailsUiEvent {
    data object NavigateBack : UserDetailsUiEvent
    data class SaveCurrentUser(val message: String): UserDetailsUiEvent
    data object ChangeAddressInfoVisibility: UserDetailsUiEvent
    data object ChangeGeneralInfoVisibility: UserDetailsUiEvent
    data object ChangeCompanyInfoVisibility: UserDetailsUiEvent
    data object ChangePositionInfoVisibility: UserDetailsUiEvent
}
