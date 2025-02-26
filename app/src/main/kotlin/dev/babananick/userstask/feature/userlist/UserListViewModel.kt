package dev.babananick.userstask.feature.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.babananick.userstask.data.UserRepository
import dev.babananick.userstask.feature.userlist.model.UserListSideEffect
import dev.babananick.userstask.feature.userlist.model.UserListState
import dev.babananick.userstask.feature.userlist.model.UserListUiEvent
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val repository: UserRepository
) : ViewModel() {
    private val _stateFlow = MutableStateFlow(UserListState())
    val stateFlow = _stateFlow.onStart {
        fetchList()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = _stateFlow.value
    )

    private val sideEffectChannel = Channel<UserListSideEffect>()
    val sideEffectFlow: Flow<UserListSideEffect>
        get() = sideEffectChannel.receiveAsFlow().flowOn(Dispatchers.Main.immediate)

    fun dispatch(uiEvent: UserListUiEvent) {
        when(uiEvent){
            is UserListUiEvent.NavigateToUserDetails -> navigateToDetails(uiEvent.id)
            UserListUiEvent.UpdateList -> refreshList()
            UserListUiEvent.Retry -> retry()
        }
    }

    private fun reduceState(reducer: (UserListState) -> UserListState) {
        _stateFlow.update(reducer)
    }

    private fun sendSideEffect(sideEffect: UserListSideEffect) {
        viewModelScope.launch {
            sideEffectChannel.send(sideEffect)
        }
    }

    private fun navigateToDetails(id: Int){
        sendSideEffect(UserListSideEffect.NavigateToDetails(id))
    }

    private fun refreshList(){
        reduceState { it.copy(isRefreshing = true) }
        viewModelScope.launch {
            fetchList()
        }
    }

    private fun retry(){
        reduceState { it.copy(isLoading = true) }
        viewModelScope.launch {
            fetchList()
        }
    }

    private suspend fun fetchList(){
        repository.getSimplifiedUsers()
            .onSuccess { dataWithSource ->
                reduceState { state ->
                    state.copy(
                        isLoading = false,
                        isRefreshing = false,
                        userList = dataWithSource.data.toImmutableList(),
                        source = dataWithSource.source
                    )
                }
                repository.saveSimplifiedUsers(dataWithSource.data)
            }
            .onFailure {
                reduceState { state ->
                    state.copy(
                        isLoading = false,
                        isRefreshing = false,
                    )
                }
            }
    }
}
