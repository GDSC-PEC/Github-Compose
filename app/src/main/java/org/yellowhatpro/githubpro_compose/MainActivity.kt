package org.yellowhatpro.githubpro_compose

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.yellowhatpro.githubpro_compose.presentation.GithubProComposeNavigation
import org.yellowhatpro.githubpro_compose.presentation.features.ui.GithubProComposeViewModel
import org.yellowhatpro.githubpro_compose.presentation.components.BottomNavigationBar
import org.yellowhatpro.githubpro_compose.theme.GithubProComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var sharedPreference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreference = this.getSharedPreferences("username", Context.MODE_PRIVATE)
        setContent {
            var dialogState by rememberSaveable{
                mutableStateOf(true)
            }
            var userState by rememberSaveable {
                mutableStateOf("")
            }
            if (!getSharedPreferences("username", MODE_PRIVATE).getString("githubusername", "").isNullOrEmpty()){
                dialogState = false
            }
            val navController = rememberNavController()
            GithubProComposeTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            navController
                        )
                    },

                    ) { paddingValues ->

                    if (dialogState) {
                        AlertDialog(onDismissRequest = { /*TODO*/ },
                            title = {
                            Text(text = "Enter Github Username")
                            },
                            text = {
                                TextField(value = userState,
                                    onValueChange = {
                                        userState = it
                                    })
                            },
                            confirmButton = {
                               TextButton(onClick = {
                                   if (userState!=""){
                                       sharedPreference.edit().putString("githubusername", userState).apply()
                                       userState = ""
                                       dialogState = false
                                   }
                               }) {
                                    Text(text = "Enter")
                               }
                            },
                            dismissButton = {
                                TextButton(onClick = { dialogState = false }) {
                                    Text(text = "Dismiss")
                                }
                            })
                    } else {
                        //Main Stuff
                        val viewModel = hiltViewModel<GithubProComposeViewModel>()
                        GithubProComposeNavigation(
                            modifier = Modifier.padding(paddingValues),
                            navHostController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}