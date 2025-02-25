package dev.babananick.userstask.feature.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.babananick.userstask.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.flowOn
import dev.babananick.userstask.feature.userdetail.model.UserDetailsState
import dev.babananick.userstask.feature.userdetail.model.UserDetailsSideEffect
import dev.babananick.userstask.feature.userdetail.model.UserDetailsUiEvent
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class UserDetailsViewModel(
    private val id: Int,
    private val repository: UserRepository
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(UserDetailsState())
    val stateFlow = _stateFlow.onStart {
        fetchUser()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = _stateFlow.value
    )

    private val sideEffectChannel = Channel<UserDetailsSideEffect>()
    val sideEffectFlow: Flow<UserDetailsSideEffect>
        get() = sideEffectChannel.receiveAsFlow().flowOn(Dispatchers.Main.immediate)

    fun dispatch(uiEvent: UserDetailsUiEvent) {
        when (uiEvent) {
            UserDetailsUiEvent.NavigateBack -> navigateBack()
            is UserDetailsUiEvent.SaveCurrentUser -> saveUser(uiEvent.message)
            UserDetailsUiEvent.ChangeAddressInfoVisibility -> changeAddressVisibility()
            UserDetailsUiEvent.ChangeCompanyInfoVisibility -> changeCompanyInfoVisibility()
            UserDetailsUiEvent.ChangeGeneralInfoVisibility -> changeGeneralInfoVisibility()
            UserDetailsUiEvent.ChangePositionInfoVisibility -> changePositionInfoVisibility()
        }
    }

    private fun reduceState(reducer: (UserDetailsState) -> UserDetailsState) {
        _stateFlow.update(reducer)
    }

    private fun sendSideEffect(sideEffect: UserDetailsSideEffect) {
        viewModelScope.launch {
            sideEffectChannel.send(sideEffect)
        }
    }

    private fun navigateBack() {
        sendSideEffect(UserDetailsSideEffect.NavigateBack)
    }

    private fun saveUser(message: String){
        _stateFlow.value.currentUser.let { user ->
            viewModelScope.launch {
                repository.saveUserDetails(user)
                sendSideEffect(UserDetailsSideEffect.ShowSaveSnackBar(message))
            }
        }
    }

    private fun changeAddressVisibility(){
        reduceState{ it.copy(isAddressExpanded = !it.isAddressExpanded)}
    }

    private fun changeCompanyInfoVisibility(){
        reduceState{ it.copy(isCompanyInfoExpanded = !it.isCompanyInfoExpanded)}
    }

    private fun changeGeneralInfoVisibility(){
        reduceState{ it.copy(isGeneralInfoExpanded = !it.isGeneralInfoExpanded)}
    }

    private fun changePositionInfoVisibility(){
        reduceState{ it.copy(isPositionExpanded = !it.isPositionExpanded)}
    }

    private suspend fun fetchUser(){
        repository.getUserById(id)
            .onSuccess { dataWithSource ->
                reduceState {
                    it.copy(
                        isLoading = false,
                        currentUser = dataWithSource.data,
                        source = dataWithSource.source
                    )
                }
            }
            .onFailure {
                reduceState { state ->
                    state.copy(isLoading = false)
                }
            }
    }
}
