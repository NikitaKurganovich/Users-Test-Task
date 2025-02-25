package dev.babananick.userstask.feature.userlist

import androidx.navigation.NavHostController
import dev.babananick.userstask.feature.userdetail.ui.UserDetailsDestination

class UserListRouterImpl(
    private val navHostController: NavHostController
) : UserListRouter {
    override fun navigateToDetails(id: Int){
        navHostController.navigate(UserDetailsDestination(id))
    }
}