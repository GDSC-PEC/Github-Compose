package org.yellowhatpro.githubpro_compose.data.entities

data class Issues(
    val incomplete_results: Boolean? = null,
    val issues: List<Issue>? = null,
    val total_count: Int? = null
)