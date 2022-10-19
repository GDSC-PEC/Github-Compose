package org.yellowhatpro.githubpro_compose.presentation.features.ui.repository

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ForkLeft
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import org.yellowhatpro.githubpro_compose.presentation.features.ui.GithubProComposeViewModel
import org.yellowhatpro.githubpro_compose.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryScreen(
    navHostController: NavHostController,
    viewModel: GithubProComposeViewModel,
    repositoryTitle: String
) {
    val userName = viewModel.userDetails.collectAsState().value.login
    viewModel.repoSearch("$userName/$repositoryTitle")
    val currentRepo = viewModel.currentRepository.collectAsState()
    Scaffold(topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .clickable { navHostController.navigateUp() }
            )
        }
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(10.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    AsyncImage(
                        model = currentRepo.value.owner?.avatar_url,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(20.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = currentRepo.value.owner?.login.toString(),
                        color = Color.Gray
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = currentRepo.value.name.toString(),
                    fontSize = 20.sp
                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = currentRepo.value.description.toString(),
                    color = Color.Gray
                )
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "",
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(
                        text = "${currentRepo.value.stargazers_count.toString()} stars",
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Icon(
                        imageVector = Icons.Outlined.ForkLeft,
                        contentDescription = "",
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(
                        text = "${currentRepo.value.forks_count.toString()} forks",
                        modifier = Modifier.padding(end = 5.dp)
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                if (currentRepo.value.parent != null) {
                    Text(text = "Forked from ${currentRepo.value.parent!!.full_name.toString()}")
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Language : ${currentRepo.value.language.toString()}")
            }
            items(currentRepo.value.topics?.size ?: 0){
                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.background(Purple40)){
                    Text(text = currentRepo.value.topics?.get(it)?.toString() ?: "")
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Open Issues : ${currentRepo.value.open_issues_count.toString()}")
            }
        }
    }
}