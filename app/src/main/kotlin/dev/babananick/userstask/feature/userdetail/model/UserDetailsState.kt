package dev.babananick.userstask.feature.userdetail.model

import androidx.compose.runtime.Immutable
import dev.babananick.userstask.model.DataWithSource
import dev.babananick.userstask.model.User

@Immutable
data class UserDetailsState(
    val isLoading: Boolean,
    val currentUser: User,
    val source: DataWithSource.Source,
    val isGeneralInfoExpanded: Boolean,
    val isAddressExpanded: Boolean,
    val isCompanyInfoExpanded: Boolean,
    val isPositionExpanded: Boolean
) {
    constructor() : this(
        isLoading = true,
        currentUser = User(),
        source = DataWithSource.Source.NONE,
        isAddressExpanded = false,
        isGeneralInfoExpanded = false,
        isCompanyInfoExpanded = false,
        isPositionExpanded = false,
    )
}
