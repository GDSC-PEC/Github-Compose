package org.yellowhatpro.githubpro_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.yellowhatpro.githubpro_compose.presentation.GithubProComposeNavigation
import org.yellowhatpro.githubpro_compose.presentation.GithubProComposeViewModel
import org.yellowhatpro.githubpro_compose.presentation.components.BottomNavigationBar
import org.yellowhatpro.githubpro_compose.ui.theme.GithubProComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel = hiltViewModel<GithubProComposeViewModel>()
            GithubProComposeTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            navController
                        )
                    }
                ) {
                    GithubProComposeNavigation(
                        navHostController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}