package org.yellowhatpro.githubpro_compose.data

import org.yellowhatpro.githubpro_compose.data.entities.GithubRepository
import org.yellowhatpro.githubpro_compose.data.entities.GithubUser
import org.yellowhatpro.githubpro_compose.data.entities.Issues
import retrofit2.Response

interface MainRepository {
    suspend fun getUserDetails() : Response<GithubUser>
    suspend fun getUserRepositories() : Response<List<GithubRepository>>
    suspend fun getUserIssues() : Response<Issues>
}