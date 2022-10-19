package org.yellowhatpro.githubpro_compose.data.entities

data class TopicSearch(
    val incomplete_results: Boolean?=null,
    val items: List<Item>?=null,
    val total_count: Int?=null
)