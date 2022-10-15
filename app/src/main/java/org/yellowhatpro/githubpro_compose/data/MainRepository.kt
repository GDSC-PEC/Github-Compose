package org.yellowhatpro.githubpro_compose.data

import org.yellowhatpro.githubpro_compose.data.entities.GithubUser
import retrofit2.Response

interface MainRepository {
    suspend fun getUserDetails() : Response<GithubUser>
}