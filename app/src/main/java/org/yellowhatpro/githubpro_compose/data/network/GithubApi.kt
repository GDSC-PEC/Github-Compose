package org.yellowhatpro.githubpro_compose.data.network

import org.yellowhatpro.githubpro_compose.data.entities.GithubRepository
import org.yellowhatpro.githubpro_compose.data.entities.GithubUser
import org.yellowhatpro.githubpro_compose.data.entities.Issues
import org.yellowhatpro.githubpro_compose.data.entities.TopicSearch
import org.yellowhatpro.githubpro_compose.utils.Constants.BEARERTOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users/{username}")
    suspend fun getUserProfile(
        @Path(value = "username", encoded = true)
        userName: String,
        @Header("Authorization")
        bearerToken: String=BEARERTOKEN,
    ) : Response<GithubUser>

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(
        @Path(value = "username", encoded = true)
        userName: String
    ) : Response<List<GithubRepository>>

    @GET("search/issues")
    suspend fun getIssues(
        @Query("q")
        query: String
    ) : Response<Issues>

    @GET("search/topics")
    suspend fun getSearchTopicResults(
        @Query("q")
        query: String
    ) : Response<TopicSearch>
}