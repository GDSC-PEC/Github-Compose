package org.yellowhatpro.githubpro_compose.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.yellowhatpro.githubpro_compose.presentation.components.NavigationItem
import org.yellowhatpro.githubpro_compose.presentation.features.ui.home.HomeScreen
import org.yellowhatpro.githubpro_compose.presentation.features.ui.notifications.NotificationsScreen
import org.yellowhatpro.githubpro_compose.presentation.features.ui.profile.ProfileScreen


@Composable
fun GithubProComposeNavigation(
    navHostController: NavHostController,
    viewModel : GithubProComposeViewModel
) {
    NavHost(navController = navHostController, startDestination = NavigationItem.Home.route ){
        composable(route = NavigationItem.Home.route){
            HomeScreen(navHostController = navHostController)
        }
        composable(route = NavigationItem.Notification.route){
            NotificationsScreen()
        }
        composable(route = NavigationItem.Profile.route){
            ProfileScreen()
        }
    }
}