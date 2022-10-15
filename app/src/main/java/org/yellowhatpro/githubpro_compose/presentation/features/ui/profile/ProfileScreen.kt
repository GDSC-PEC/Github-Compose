package org.yellowhatpro.githubpro_compose.presentation.features.ui.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.yellowhatpro.githubpro_compose.presentation.features.ui.GithubProComposeViewModel

@Composable
fun ProfileScreen(viewModel: GithubProComposeViewModel) {
    val userDetails = viewModel.userDetails
    Text(text = userDetails.value.name?: "")
}