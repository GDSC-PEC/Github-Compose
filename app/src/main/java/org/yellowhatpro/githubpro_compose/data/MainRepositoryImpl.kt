package org.yellowhatpro.githubpro_compose.data

import android.content.Context
import dagger.hilt.android.scopes.ActivityScoped
import org.yellowhatpro.githubpro_compose.data.entities.GithubRepository
import org.yellowhatpro.githubpro_compose.data.entities.GithubUser
import org.yellowhatpro.githubpro_compose.data.entities.Issues
import org.yellowhatpro.githubpro_compose.data.entities.TopicSearch
import org.yellowhatpro.githubpro_compose.data.network.GithubApi
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class MainRepositoryImpl @Inject constructor(private val githubApi: GithubApi,
                         context: Context) : MainRepository {
    private val sharedPreferences = context.getSharedPreferences("username", Context.MODE_PRIVATE)
    private val username = sharedPreferences.getString("githubusername", "") ?: ""
    override suspend fun getUserDetails(): Response<GithubUser> =
        githubApi.getUserProfile(username)

    override suspend fun getUserRepositories(): Response<List<GithubRepository>> =
        githubApi.getUserRepositories(username)

    override suspend fun getUserIssues(): Response<Issues> =
        githubApi.getIssues(username)

    override suspend fun topicSearch(q: String): Response<TopicSearch> =
        githubApi.getSearchTopicResults(q)
}