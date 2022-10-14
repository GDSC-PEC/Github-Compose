package org.yellowhatpro.githubpro_compose.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.yellowhatpro.githubpro_compose.data.MainRepository
import javax.inject.Inject

@HiltViewModel
class GithubProComposeViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
}