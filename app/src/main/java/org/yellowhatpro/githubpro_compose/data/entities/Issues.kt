package org.yellowhatpro.githubpro_compose.data.entities

data class Issues(
    val incomplete_results: Boolean? = null,
    val items: List<Issue>? = null,
    val total_count: Int? = null
)