package dev.babananick.userstask.feature.userdetail.model

import androidx.compose.runtime.Immutable

@Immutable
sealed interface UserDetailsSideEffect {
    data object NavigateBack: UserDetailsSideEffect
    data class ShowSaveSnackBar(val message: String) : UserDetailsSideEffect
}
