package br.com.gifs.data.model

data class Pagination(
    val total_count: Int,
    val count: Int,
    val offset: Int
)

data class Meta(
    val status: Int,
    val msg: String,
    val response_id: String
)