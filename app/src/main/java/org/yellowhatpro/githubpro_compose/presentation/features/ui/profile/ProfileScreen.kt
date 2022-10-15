package org.yellowhatpro.githubpro_compose.presentation.features.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.yellowhatpro.githubpro_compose.presentation.features.ui.GithubProComposeViewModel

@Composable
fun ProfileScreen(viewModel: GithubProComposeViewModel) {
    val userDetails = viewModel.userDetails.collectAsState()
    LazyColumn(modifier = Modifier.padding(20.dp)){
        item{
            Column{
                Row {
                    AsyncImage(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape),
                        model = userDetails.value.avatar_url,
                            contentDescription = "")
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = userDetails.value.name?:"", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                        Text(text = userDetails.value.login ?: "", color = Color.Gray)
                    }
                }
                Column(modifier = Modifier.padding(top =10.dp )) {
                    Text(text = userDetails.value.bio?:"")
                    Row {
                        Icon(imageVector = Icons.Rounded.Link, contentDescription = "")
                        Text(text = userDetails.value.blog ?: "", fontWeight = FontWeight.Bold)
                    }
                    Row {
                        Icon(imageVector = Icons.Outlined.Person, contentDescription = "")
                        Text(text = "${userDetails.value.followers}  followers • ${userDetails.value.following} following" , fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
        item {

        }
    }
}