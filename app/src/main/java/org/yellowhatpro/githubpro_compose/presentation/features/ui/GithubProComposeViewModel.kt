package org.yellowhatpro.githubpro_compose.presentation.features.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.yellowhatpro.githubpro_compose.data.MainRepository
import org.yellowhatpro.githubpro_compose.data.entities.GithubRepository
import org.yellowhatpro.githubpro_compose.data.entities.GithubUser
import org.yellowhatpro.githubpro_compose.data.entities.Issues
import org.yellowhatpro.githubpro_compose.data.entities.Repository
import org.yellowhatpro.githubpro_compose.data.entities.TopicSearch
import javax.inject.Inject

@HiltViewModel
class GithubProComposeViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    init {
        getUserDetails()
        getUserRepositories()
        getUserIssues()
    }

    private val _userDetails = MutableStateFlow(GithubUser())
    private val _userRepositories = MutableStateFlow(listOf(GithubRepository()))
    private val _userIssues = MutableStateFlow(Issues())
    private val _topicSearchResults = MutableStateFlow(TopicSearch())
    private val _currentRepositorySelected = MutableStateFlow(Repository())
    val userDetails = _userDetails.asStateFlow()
    val userRepositories = _userRepositories.asStateFlow()
    val userIssues = _userIssues.asStateFlow()
    val topicSearchResults = _topicSearchResults.asStateFlow()
    val currentRepository = _currentRepositorySelected.asStateFlow()

    private fun getUserDetails() {
        viewModelScope.launch {
            val response = mainRepository.getUserDetails()
            if (response.isSuccessful) {
                _userDetails.value = response.body() ?: GithubUser()
            }
        }
    }

    private fun getUserRepositories() {
        viewModelScope.launch {
            val response = mainRepository.getUserRepositories()
            if (response.isSuccessful) {
                _userRepositories.value = response.body() ?: listOf(GithubRepository())
            }
        }
    }

    private fun getUserIssues() {
        viewModelScope.launch {
            val response = mainRepository.getUserIssues()
            if (response.isSuccessful) {
                _userIssues.value = response.body() ?: Issues()
            }
        }
    }

    fun topicSearch(q: String) {
        viewModelScope.launch {
            val response = mainRepository.topicSearch(q)
            if (response.isSuccessful) {
                _topicSearchResults.value = response.body() ?: TopicSearch()
            }
        }
    }

    fun repoSearch(name: String) {
        viewModelScope.launch {
            val response = mainRepository.repositorySearch(name)
            if (response.isSuccessful) {
                _currentRepositorySelected.value = response.body() ?: Repository()
            }
        }
    }
}