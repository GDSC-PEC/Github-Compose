package org.yellowhatpro.githubpro_compose.presentation.features.ui.issues

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.yellowhatpro.githubpro_compose.presentation.features.ui.GithubProComposeViewModel

@Composable
fun IssuesScreen(viewModel: GithubProComposeViewModel) {
    val issues = viewModel.userIssues
    LazyColumn{
        items(issues.value.issues?: listOf()){
            Text(text = it.title ?: "")
        }
    }
}