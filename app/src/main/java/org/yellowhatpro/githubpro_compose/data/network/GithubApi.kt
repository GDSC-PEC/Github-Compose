package org.yellowhatpro.githubpro_compose.data.network

import org.yellowhatpro.githubpro_compose.data.entities.GithubUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users/{username}")
    suspend fun getUserProfile(
        @Path(value = "username", encoded = true)
        userName: String
    ) : Response<GithubUser>
}