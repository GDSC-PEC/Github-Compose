package org.yellowhatpro.githubpro_compose.data.entities

data class PullRequest(
    val diff_url: String,
    val html_url: String,
    val merged_at: String,
    val patch_url: String,
    val url: String
)