package org.yellowhatpro.githubpro_compose.presentation.features.ui

import android.app.Activity
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.yellowhatpro.githubpro_compose.data.MainRepository
import org.yellowhatpro.githubpro_compose.data.entities.GithubUser
import javax.inject.Inject

@HiltViewModel
class GithubProComposeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {
    init {
        getUserDetails()
    }
    private val _userDetails = MutableStateFlow(GithubUser())
    val userDetails = _userDetails.asStateFlow()

    fun getUserDetails(){
        viewModelScope.launch {
            val response = mainRepository.getUserDetails()
            if(response.isSuccessful){
                _userDetails.value = response.body() ?: GithubUser()
            }
        }
    }
}