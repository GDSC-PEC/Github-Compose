package org.yellowhatpro.githubpro_compose.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.yellowhatpro.githubpro_compose.presentation.components.NavigationItem
import org.yellowhatpro.githubpro_compose.presentation.features.ui.GithubProComposeViewModel
import org.yellowhatpro.githubpro_compose.presentation.features.ui.home.HomeScreen
import org.yellowhatpro.githubpro_compose.presentation.features.ui.issues.IssuesScreen
import org.yellowhatpro.githubpro_compose.presentation.features.ui.profile.ProfileScreen


@Composable
fun GithubProComposeNavigation(
    modifier : Modifier = Modifier,
    navHostController: NavHostController,
    viewModel : GithubProComposeViewModel
) {
    NavHost(navController = navHostController, startDestination = NavigationItem.Home.route ){
        composable(route = NavigationItem.Home.route){
            HomeScreen(navHostController = navHostController, viewModel)
        }
        composable(route = NavigationItem.Issues.route){
            IssuesScreen(viewModel)
        }
        composable(route = NavigationItem.Profile.route){
            ProfileScreen(viewModel)
        }
    }
}