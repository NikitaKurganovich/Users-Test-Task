package dev.babananick.userstask

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.babananick.userstask.feature.userdetail.UserDetailsRouterImpl
import dev.babananick.userstask.feature.userdetail.UserDetailsViewModel
import dev.babananick.userstask.feature.userdetail.ui.UserDetailsDestination
import dev.babananick.userstask.feature.userdetail.ui.UserDetailsScreen
import dev.babananick.userstask.feature.userlist.UserListRouterImpl
import dev.babananick.userstask.feature.userlist.UserListViewModel
import dev.babananick.userstask.feature.userlist.ui.UserListDestination
import dev.babananick.userstask.feature.userlist.ui.UserListScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppHavHost(
    modifier: Modifier = Modifier
){
    val userListViewModel = koinViewModel<UserListViewModel>()
    val navController = rememberNavController()
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = UserListDestination,
    ) {
        composable<UserListDestination> {
            UserListScreen(
                viewModel = userListViewModel,
                router = UserListRouterImpl(navController)
            )
        }
        composable<UserDetailsDestination> {backStackEntry ->
            val profile: UserDetailsDestination = backStackEntry.toRoute()
            val detailsViewModel = koinViewModel<UserDetailsViewModel>{
                parametersOf(profile.id)
            }
            UserDetailsScreen(
                viewModel = detailsViewModel,
                router = UserDetailsRouterImpl(navController)
            )
        }
    }
}

