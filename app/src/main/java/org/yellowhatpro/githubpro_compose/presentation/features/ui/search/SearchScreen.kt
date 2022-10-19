package org.yellowhatpro.githubpro_compose.presentation.features.ui.search

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.yellowhatpro.githubpro_compose.presentation.features.ui.GithubProComposeViewModel
import org.yellowhatpro.githubpro_compose.presentation.features.ui.profile.LottieAnim

@Composable
fun SearchScreen(
    viewModel: GithubProComposeViewModel,
    modifier: Modifier = Modifier
) {
    val searchTextState = remember {
        mutableStateOf(TextFieldValue(""))
    }
    val topicSearchResults by viewModel.topicSearchResults.collectAsState()
    val results = topicSearchResults.items ?: listOf()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchView(state = searchTextState, viewModel = viewModel)
        if (searchTextState.value==TextFieldValue("")){
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                LottieAnim()
                Text(text = "Search any GitHub topics", color = Color.Gray)
            }
    } else {
            LazyColumn {
                items(results) {
                    SearchItem(
                        displayName = it.display_name?.toString() ?: it.name ?:"",
                        desc = it.description?.toString() ?: "",
                        createdBy = "Created by : ${it.created_by?.toString() ?: ""}",
                        released = "Released : ${it.released?.toString() ?: ""}"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>,
    viewModel: GithubProComposeViewModel) {
    Box {
        TextField(
            value = state.value, onValueChange = { value ->
                state.value = value
            },
            placeholder = {
                          Text(text = "Search Topics")
            },
            leadingIcon = {},
            trailingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(24.dp)
                        .clickable {
                            viewModel.topicSearch(state.value.text)
                        },
                    tint = if (state.value != TextFieldValue("")) {
                        Color.Green
                    } else Color.Gray
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.textFieldColors(

                textColor = Color.White,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun SearchItem(displayName: String,
    desc: String,
    createdBy: String,
    released: String) {
    Box(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
        .border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
        .padding(5.dp)){
        Column{
            Text(text = displayName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = desc)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = createdBy)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = released )
        }
    }
}