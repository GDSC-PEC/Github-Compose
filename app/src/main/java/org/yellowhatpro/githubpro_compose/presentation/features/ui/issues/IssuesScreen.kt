package org.yellowhatpro.githubpro_compose.presentation.features.ui.issues

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.yellowhatpro.githubpro_compose.presentation.features.ui.GithubProComposeViewModel
import org.yellowhatpro.githubpro_compose.theme.Purple40

@Composable
fun IssuesScreen(viewModel: GithubProComposeViewModel,
    modifier : Modifier = Modifier
) {
    val issues = viewModel.userIssues.collectAsState().value
    val issueList = issues.items ?: listOf()
    Column (modifier = modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Text(text = "Total Issues:  ${issues.total_count.toString()}",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Divider(modifier = Modifier
            .height(2.dp)
            .background(Color.Gray)
            .padding(10.dp))
        LazyColumn{
            items(issueList){
                IssueBox(title = it.title.toString(),
                    state = it.state.toString(),
                    relatedRepo = it.repository_url.toString(),
                    color = if (it.state.toString()=="open") Color.Green else Purple40)

            }
        }
    }
}

@Composable
fun IssueBox(
    title: String,
    state: String,
    relatedRepo: String,
    color : Color
){
    Box(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()
        .border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
        .padding(5.dp)){
        Column{
            Text(text = "Title:  $title")
            Text(text = "Repository: ${relatedRepo.removeRange(0,29)}")
            Text(text = state, color = color)
        }
    }
}