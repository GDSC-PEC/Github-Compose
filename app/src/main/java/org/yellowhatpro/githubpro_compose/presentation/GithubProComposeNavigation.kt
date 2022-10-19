package org.yellowhatpro.githubpro_compose.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.yellowhatpro.githubpro_compose.presentation.components.NavigationItem
import org.yellowhatpro.githubpro_compose.presentation.features.ui.GithubProComposeViewModel
import org.yellowhatpro.githubpro_compose.presentation.features.ui.search.SearchScreen
import org.yellowhatpro.githubpro_compose.presentation.features.ui.issues.IssuesScreen
import org.yellowhatpro.githubpro_compose.presentation.features.ui.profile.ProfileScreen
import org.yellowhatpro.githubpro_compose.presentation.features.ui.repository.RepositoryScreen

@Composable
fun GithubProComposeNavigation(
    modifier : Modifier = Modifier,
    navHostController: NavHostController,
    viewModel : GithubProComposeViewModel
) {
    NavHost(navController = navHostController, startDestination = NavigationItem.Search.route) {
        composable(route = NavigationItem.Search.route) {
            SearchScreen(
                viewModel,
                modifier = modifier
            )
        }
        composable(route = NavigationItem.Issues.route) {
            IssuesScreen(viewModel, modifier = modifier)
        }
        composable(route = NavigationItem.Profile.route) {
            ProfileScreen(viewModel, modifier = modifier,
                navHostController = navHostController)
        }
        composable(
            route = "repo/{repository}",
            arguments = listOf(navArgument("repository") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("repository")?.let { title ->
                RepositoryScreen(
                    navHostController = navHostController,
                    viewModel = viewModel,
                    repositoryTitle = title
                )
            }
        }
    }
}