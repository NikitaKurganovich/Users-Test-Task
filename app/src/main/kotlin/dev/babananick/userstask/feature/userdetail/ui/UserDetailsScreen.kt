package dev.babananick.userstask.feature.userdetail.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import dev.babananick.userstask.R
import dev.babananick.userstask.feature.userdetail.UserDetailsRouter
import dev.babananick.userstask.feature.userdetail.UserDetailsViewModel
import dev.babananick.userstask.feature.userdetail.model.UserDetailsSideEffect
import dev.babananick.userstask.feature.userdetail.model.UserDetailsState
import dev.babananick.userstask.feature.userdetail.model.UserDetailsUiEvent
import dev.babananick.userstask.model.Address
import dev.babananick.userstask.model.Company
import dev.babananick.userstask.model.DataWithSource
import dev.babananick.userstask.model.Geo
import dev.babananick.userstask.model.User
import dev.babananick.userstask.ui.kit.icons.ArrowCircleRight
import dev.babananick.userstask.ui.kit.icons.Briefcase
import dev.babananick.userstask.ui.kit.icons.ChevronDown
import dev.babananick.userstask.ui.kit.icons.Location
import dev.babananick.userstask.ui.kit.icons.Map
import dev.babananick.userstask.ui.theme.LocalSnackBarHost
import dev.babananick.userstask.ui.theme.UsersTaskTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun UserDetailsScreen(
    viewModel: UserDetailsViewModel,
    router: UserDetailsRouter,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val scope = rememberCoroutineScope()
    val snackbarHostState = LocalSnackBarHost.current
    LaunchedEffect(viewModel) {
        viewModel.sideEffectFlow
            .flowWithLifecycle(lifecycle)
            .onEach { sideEffect: UserDetailsSideEffect ->
                handleSideEffect(
                    sideEffect = sideEffect,
                    router = router,
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
            }
            .launchIn(this)
    }

    val state: UserDetailsState by viewModel.stateFlow.collectAsStateWithLifecycle()

    UserDetailsStateContent(
        state = state,
        onEvent = { uiEvent: UserDetailsUiEvent -> viewModel.dispatch(uiEvent) }
    )
}

private fun handleSideEffect(
    sideEffect: UserDetailsSideEffect,
    router: UserDetailsRouter,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    when (sideEffect) {
        UserDetailsSideEffect.NavigateBack -> router.navigateBack()
        is UserDetailsSideEffect.ShowSaveSnackBar -> scope.launch {
            snackbarHostState.showSnackbar(sideEffect.message)
        }
    }
}

@Composable
private fun UserDetailsStateContent(
    state: UserDetailsState,
    onEvent: (UserDetailsUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.currentUser == User()) {
            BackIconButton { onEvent(UserDetailsUiEvent.NavigateBack) }
            Text(stringResource(R.string.user_cant_be_fetched))
        } else {
            BackIconButton { onEvent(UserDetailsUiEvent.NavigateBack) }
            UserData(
                state = state,
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun BoxScope.BackIconButton(
    onClick: () -> Unit
){
    IconButton(
        modifier = Modifier.align(Alignment.TopStart),
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.rotate(180f).size(32.dp),
            imageVector = ArrowCircleRight,
            contentDescription = stringResource(R.string.user_data_navigate_back_button)
        )
    }
}


@Composable
private fun UserData(
    state: UserDetailsState,
    onEvent: (UserDetailsUiEvent) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserSection(
            state = state,
            onEvent = onEvent
        )
        AddressSection(
            state = remember(
                state.isAddressExpanded,
                state.isPositionExpanded
            ) { state },
            onEvent = onEvent
        )
        CompanySection(
            state = remember(state.isCompanyInfoExpanded) { state },
            onEvent = onEvent
        )
        val message = stringResource(R.string.user_data_saved_user_message, state.currentUser.name)
        Button(
            onClick = remember {
                {
                    onEvent(UserDetailsUiEvent.SaveCurrentUser(message))
                }
            }
        ) {
            Text(stringResource(R.string.user_data_save_button_text))
        }
    }
}

@Composable
private fun UserSection(
    state: UserDetailsState,
    onEvent: (UserDetailsUiEvent) -> Unit
) {

    Column(modifier = Modifier) {
        SectionHeader(
            icon = remember { dev.babananick.userstask.ui.kit.icons.User },
            title = stringResource(R.string.user_data_general_title, state.currentUser.name),
            expanded = remember(state.isGeneralInfoExpanded) { state.isGeneralInfoExpanded },
            onClick = { onEvent(UserDetailsUiEvent.ChangeGeneralInfoVisibility) }
        )
        AnimatedVisibility(visible = state.isGeneralInfoExpanded) {
            Column {
                Text(stringResource(R.string.user_data_user_id, state.currentUser.id))
                Text(stringResource(R.string.user_data_username, state.currentUser.username))
                Text(stringResource(R.string.user_data_email, state.currentUser.email))
                Text(stringResource(R.string.user_data_phone, state.currentUser.phone))
                Text(stringResource(R.string.user_data_website, state.currentUser.website))
            }
        }
    }
}

@Composable
fun AddressSection(
    state: UserDetailsState,
    onEvent: (UserDetailsUiEvent) -> Unit
) {
    Column(modifier = Modifier) {
        SectionHeader(
            icon = Map,
            title = stringResource(R.string.user_data_address_title),
            expanded = remember(state.isAddressExpanded) { state.isAddressExpanded },
            onClick = { onEvent(UserDetailsUiEvent.ChangeAddressInfoVisibility) }
        )
        AnimatedVisibility(visible = state.isAddressExpanded) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    stringResource(
                        R.string.user_data_address_street,
                        state.currentUser.address.street
                    )
                )
                Text(
                    stringResource(
                        R.string.user_data_address_suite,
                        state.currentUser.address.suite
                    )
                )
                Text(
                    stringResource(
                        R.string.user_data_address_city,
                        state.currentUser.address.city
                    )
                )
                Text(
                    stringResource(
                        R.string.user_data_address_zipcode,
                        state.currentUser.address.zipcode
                    )
                )
                GeoSection(
                    state = remember(state.isPositionExpanded) { state },
                    onEvent = onEvent
                )
            }
        }
    }
}

@Composable
private fun GeoSection(
    state: UserDetailsState,
    onEvent: (UserDetailsUiEvent) -> Unit
) {

    Column(modifier = Modifier) {
        SectionHeader(
            icon = Location,
            title = stringResource(R.string.user_data_geo_title),
            remember(state.isPositionExpanded) { state.isPositionExpanded },
            onClick = { onEvent(UserDetailsUiEvent.ChangePositionInfoVisibility) }
        )

        AnimatedVisibility(visible = state.isPositionExpanded) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.user_data_geo_lat, state.currentUser.address.geo.lat))
                Text(stringResource(R.string.user_data_geo_lng, state.currentUser.address.geo.lng))
            }
        }
    }
}

@Composable
private fun CompanySection(
    state: UserDetailsState,
    onEvent: (UserDetailsUiEvent) -> Unit
) {
    Column(modifier = Modifier) {
        SectionHeader(
            icon = Briefcase,
            title = stringResource(R.string.user_data_company_title),
            remember(state.isCompanyInfoExpanded) { state.isCompanyInfoExpanded },
            onClick = { onEvent(UserDetailsUiEvent.ChangeCompanyInfoVisibility) }
        )
        AnimatedVisibility(visible = state.isCompanyInfoExpanded) {
            Column {
                Text(
                    stringResource(
                        R.string.user_data_company_name,
                        state.currentUser.company.name
                    )
                )
                Text(
                    stringResource(
                        R.string.user_data_company_catch_phrase,
                        state.currentUser.company.catchPhrase
                    )
                )
                Text(stringResource(R.string.user_data_company_bs, state.currentUser.company.bs))
            }
        }
    }
}

@Composable
private fun SectionHeader(
    icon: ImageVector,
    title: String,
    expanded: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(48.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(modifier = Modifier.size(32.dp), imageVector = icon, contentDescription = title)
            Text(title, style = MaterialTheme.typography.titleLarge)
        }
        IconButton(onClick = onClick) {
            val rotation by animateFloatAsState(
                targetValue = if (expanded) 180f else 0f,
                animationSpec = tween(durationMillis = 600)
            )
            Icon(
                modifier = Modifier.graphicsLayer{
                    rotationZ = rotation
                },
                imageVector = remember { ChevronDown },
                contentDescription = stringResource(
                    if (expanded) R.string.acsb_user_data_icon_close
                    else R.string.acsb_user_data_icon_open
                )
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun UserDetailsStateContentPreview() {
    UsersTaskTheme {
        UserDetailsStateContent(
            state = UserDetailsState(
                isLoading = false,
                source = DataWithSource.Source.REMOTE,
                currentUser = dummyUser,
                isPositionExpanded = false,
                isGeneralInfoExpanded = false,
                isAddressExpanded = false,
                isCompanyInfoExpanded = false
            ),
            onEvent = {}
        )
    }
}

private val dummyUser = User(
    id = 1,
    name = "John Doe",
    username = "johndoe",
    email = "johndoe@example.com",
    address = Address(
        street = "123 Main St",
        suite = "Apt 4B",
        city = "Springfield",
        zipcode = "12345",
        geo = Geo(
            lat = "37.7749",
            lng = "-122.4194"
        )
    ),
    phone = "123-456-7890",
    website = "https://johndoe.com",
    company = Company(
        name = "Acme Corp",
        catchPhrase = "Innovating the Future",
        bs = "business strategy"
    )
)
