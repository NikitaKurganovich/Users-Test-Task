package dev.babananick.userstask.feature.userdetail

import androidx.navigation.NavHostController

class UserDetailsRouterImpl(
    private val navHostController: NavHostController
) : UserDetailsRouter {
    override fun navigateBack() {
        navHostController.popBackStack()
    }
}