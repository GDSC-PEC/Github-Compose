package org.yellowhatpro.githubpro_compose.presentation.features.ui.home

import android.widget.SearchView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import org.yellowhatpro.githubpro_compose.data.entities.TopicSearch
import org.yellowhatpro.githubpro_compose.presentation.features.ui.GithubProComposeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: GithubProComposeViewModel,
    modifier : Modifier = Modifier
) {
    val topicSearchResults by viewModel.topicSearchResults.collectAsState()
    val results = topicSearchResults.items
    Column(modifier = modifier.fillMaxSize()) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>,
    viewModel: GithubProComposeViewModel){
    val searchStarted by remember {
        mutableStateOf(false)
    }
    Box {
        TextField(value = state.value, onValueChange = {value->
            state.value = value
        })
    }

}