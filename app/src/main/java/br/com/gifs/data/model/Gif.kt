package br.com.gifs.data.model

data class Gif(
    val id: String,
    val title: String,
    val trending_datetime: String? = null,
    val images: Images? = null
)

data class Images(
    val original: Original?
)

data class Original(
    val url: String,
    val hash: String
)