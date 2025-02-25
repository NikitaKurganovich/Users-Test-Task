package dev.babananick.userstask.feature.userlist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import dev.babananick.userstask.R
import dev.babananick.userstask.feature.userlist.UserListRouter
import dev.babananick.userstask.feature.userlist.UserListViewModel
import dev.babananick.userstask.feature.userlist.model.UserListSideEffect
import dev.babananick.userstask.feature.userlist.model.UserListState
import dev.babananick.userstask.feature.userlist.model.UserListUiEvent
import dev.babananick.userstask.ui.kit.SimplifiedUserItem
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun UserListScreen(
    viewModel: UserListViewModel,
    router: UserListRouter,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(viewModel) {
        viewModel.sideEffectFlow
            .flowWithLifecycle(lifecycle)
            .onEach { sideEffect: UserListSideEffect ->
                handleSideEffect(sideEffect, router)
            }
            .launchIn(this)
    }

    val state: UserListState by viewModel.stateFlow.collectAsStateWithLifecycle()

    UserListStateContent(
        state = remember(
            state.isRefreshing,
            state.userList,
            state.isLoading
        ) { state },
        onEvent = { uiEvent: UserListUiEvent -> viewModel.dispatch(uiEvent) }
    )
}

private fun handleSideEffect(sideEffect: UserListSideEffect, router: UserListRouter) {
    when (sideEffect) {
        is UserListSideEffect.NavigateToDetails -> router.navigateToDetails(sideEffect.id)
    }
}

@Composable
private fun UserListStateContent(
    state: UserListState,
    onEvent: (UserListUiEvent) -> Unit
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.userList.isEmpty()) {
            UpdatableText(onEvent = onEvent)
        } else {
            ActualContent(
                state = remember(
                    state.isRefreshing,
                    state.userList
                ) { state },
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun UpdatableText(
    onEvent: (UserListUiEvent) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(R.string.user_list_cant_be_fetched),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                onEvent(UserListUiEvent.Retry)
            }
        ) {
            Text(stringResource(R.string.user_list_update_button_text))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ActualContent(
    state: UserListState,
    onEvent: (UserListUiEvent) -> Unit
) {
    val rememberedUsers = remember(state.userList) {
        state.userList
    }
    Column {
        TextBox(state = remember(state.source) { state })
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = {
                onEvent(UserListUiEvent.UpdateList)
            }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    items = rememberedUsers,
                    key = { it.id }
                ) { user ->
                    SimplifiedUserItem(
                        user = user,
                        onClick = { onEvent(UserListUiEvent.NavigateToUserDetails(user.id)) }
                    )
                }
            }
        }
    }
}

@Composable
private fun TextBox(state: UserListState) = Box(
    modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 48.dp)
        .background(MaterialTheme.colorScheme.surfaceContainer),
    contentAlignment = Alignment.Center
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        text = stringResource(
            R.string.current_data_source_type_template,
            stringResource(state.source.id)
        ),
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center
    )
}
