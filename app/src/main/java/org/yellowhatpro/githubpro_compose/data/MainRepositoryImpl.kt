package org.yellowhatpro.githubpro_compose.data

import android.content.Context
import dagger.hilt.android.scopes.ActivityScoped
import org.yellowhatpro.githubpro_compose.data.entities.GithubUser
import org.yellowhatpro.githubpro_compose.data.network.GithubApi
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class MainRepositoryImpl @Inject constructor(private val githubApi: GithubApi,
                         private val context: Context) : MainRepository {
    override suspend fun getUserDetails(): Response<GithubUser> {
        val sharedPreferences = context.getSharedPreferences("username", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("githubusername", "") ?: ""
        return githubApi.getUserProfile(username)
    }
}